package com.jd.pims.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jd.pims.model.User;
import com.jd.pims.service.UserService;
import com.jd.pims.util.FileNameUtil;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService baseService;
	
//	public UserService getBaseService() {
//		return baseService;
//	}
//	@Autowired
//	public void setBaseService(UserService baseService) {
//		this.baseService = baseService;
//	}
	ObjectMapper mapper = new ObjectMapper();
	@SuppressWarnings("finally")
	@RequestMapping("/addInfo")
	@ResponseBody
	public String add(User add,HttpServletRequest request){
		String reply="";
		try {			
//			add.setName(new String(add.getName().getBytes("ISO-8859-1"), "utf-8"));
//			System.out.println(add.getId() + ":::::" + add.getName() + ":::::" + add.getAge()+".."+add.getEmail());
			reply = baseService.addInfo(add);
		} catch (Exception e) {
			e.printStackTrace();
			reply = "娣诲姞淇℃伅澶辫触锛佸叿浣撳紓甯镐俊鎭細" + e.getMessage();
		} finally {			
			return reply;
		}
	}
	
	@RequestMapping(value = "/getAllUser", method = RequestMethod.GET)
	@ResponseBody
	public String getAddInfoAll(HttpServletRequest request) throws Exception, JsonMappingException, IOException{
			List<User> list = baseService.getAll();
//			System.out.println(list);
//			request.setAttribute("list", list);
		
			return mapper.writeValueAsString(list);
		
	}
	/**
	 * 鍒嗛〉鏌ヨ瀵硅薄鍒楄〃
	 * @param request
	 * @return
	 * @throws Exception
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public String login(HttpServletRequest request){
		String usr= request.getParameter("usr");
		String pwd= request.getParameter("pwd");
		if("admin".equals(usr)&&"123456".equals(pwd)){
			return "success";
		}
		return "fail";
	}
	
	@SuppressWarnings("finally")
	@RequestMapping("delInfo")
	@ResponseBody
	public String del(User user,HttpServletRequest request){
		String str = "";
		try {			
			 str = baseService.delete(user.getId()+"");
		} catch (Exception e) {
			e.printStackTrace();
			str="鍒犻櫎淇℃伅澶辫触锛佸叿浣撳紓甯镐俊鎭細" + e.getMessage();
		} finally {			
			return str;
		}
	}
	@RequestMapping("/getUserById")
	public String modify(String tid,HttpServletRequest request){
		try {			
			User add = baseService.findById(tid);
//			request.setAttribute("add", add);
			return mapper.writeValueAsString(add);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("InfoMessage", "淇℃伅杞藉叆澶辫触锛佸叿浣撳紓甯镐俊鎭細" + e.getMessage());
			return null;
		}
	}
	@SuppressWarnings("finally")
	@RequestMapping("/updateUser")
	@ResponseBody
	public String update(User add,HttpServletRequest request){
		String reply="";
		try {
//			add.setName(new String(add.getName().getBytes("ISO-8859-1"), "utf-8"));
			reply = baseService.update(add);
		} catch (Exception e) {
			e.printStackTrace();
			reply="鏇存柊淇℃伅澶辫触锛佸叿浣撳紓甯镐俊鎭細" + e.getMessage();
		} finally {			
			return reply;
		}
	}
	@SuppressWarnings("finally")
	@RequestMapping("/updateIcon")
	@ResponseBody
	public String updateIcon(@RequestParam(value = "file", required = false) MultipartFile file,String id,HttpServletRequest request){
		String reply="";
		String path=request.getSession().getServletContext().getRealPath("upload");
		//鑾峰彇涓婁紶鏂囦欢鐨勫悕瀛�
//		String oriFileName = file.getOriginalFilename(); 
		//閲嶆柊璧峰悕
		String fileName = FileNameUtil.getName()+".jpg"; 
		User user = new User();
//		String requestUrl = request.getRequestURL().toString();
//		String location = requestUrl.substring(0, requestUrl.indexOf("aps"));
		user.setId(Integer.parseInt(id));
		user.setIcon(fileName);
		reply = baseService.updateIcon(user);
		
		try {
			if("success".equals(reply)){
				File targetFile = new File(path, fileName);  
		        if(!targetFile.exists()){  
		            targetFile.mkdirs();  
		        }  
		        
		        //淇濆瓨  
		        try {  
		            file.transferTo(targetFile);  
		            reply="success";
		        } catch (Exception e) {  
		            e.printStackTrace();  
		        }  
			}
	//			add.setName(new String(add.getName().getBytes("ISO-8859-1"), "utf-8"));
			
		} catch (Exception e) {
			e.printStackTrace();
			reply="鏇存柊淇℃伅澶辫触锛佸叿浣撳紓甯镐俊鎭細" + e.getMessage();
		} finally {			
			return reply;
		}
	}

}
