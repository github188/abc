package com.jd.pims.pem.controller;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.jd.pims.user.model.Employee;

@Controller
@RequestMapping("/jdemws")
public class AppController {
	@Value("${app.address}")  
    private String appAddress;
	
	/**
	 * 业务接口
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public void login(HttpServletRequest request,HttpServletResponse response){
		try {
			String usr= request.getParameter("account");
			String pwd= request.getParameter("password");
			Employee dto = new Employee();
			dto.setId("wangwangwang");
			//dto.setOrgCode("wangwangwang");
			//dto.setOrgName("wangwangwang");
			dto.setSex("18");
		    String msg=JSON.toJSONString(dto);
		    Map<String,String>map = new HashMap<String,String>();
		    map.put("result", msg);
		    map.put("message", "wangwangwang");
		    map.put("returnCode", "0");
		    String result=JSON.toJSONString(map);
		    PrintWriter out = response.getWriter();  
		    out.println(result);  
		    out.flush();  
		    out.close();  
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 业务接口
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	@ResponseBody
	public void logout(HttpServletRequest request,HttpServletResponse response){
		try {
			String empId= request.getParameter("empId");
		    Map<String,String>map = new HashMap<String,String>();
		    map.put("message", "wangwangwang");
		    map.put("returnCode", "0");
		    String result=JSON.toJSONString(map);
		    PrintWriter out = response.getWriter();  
		    out.println(result);  
		    out.flush();  
		    out.close();  
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 业务接口
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getNumberOnDuty", method = RequestMethod.POST)
	@ResponseBody
	public String getNumberOnDuty(HttpServletRequest request){
		return "SUCCESS";
	}
	
	/**
	 * 业务接口
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getEfficiency", method = RequestMethod.POST)
	@ResponseBody
	public String getEfficiency(HttpServletRequest request){
		return "SUCCESS";
	}
	
	/**
	 * 业务接口
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getNumberHistory", method = RequestMethod.POST)
	@ResponseBody
	public String getNumberHistory(HttpServletRequest request){
		return "SUCCESS";
	}
	
	/**
	 * 业务接口
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getEfficiencyHistory", method = RequestMethod.POST)
	@ResponseBody
	public String getEfficiencyHistory(HttpServletRequest request){
		return "SUCCESS";
	}
	
	/**
	 * 业务接口
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getOrganization", method = RequestMethod.POST)
	@ResponseBody
	public String getOrganization(HttpServletRequest request){
		return "SUCCESS";
	}
	
	/**
	 * 业务接口
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/verifyAccount", method = RequestMethod.POST)
	@ResponseBody
	public String verifyAccount(HttpServletRequest request){
		return "SUCCESS";
	}
	
	public String getAppAddress() {
		return appAddress;
	}

	public void setAppAddress(String appAddress) {
		this.appAddress = appAddress;
	} 
}
