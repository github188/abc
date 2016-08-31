package com.jd.pims.pem.controller;

import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jd.pims.pem.model.empDTO;
import com.jd.pims.pem.service.impl.BizServiceImpl;
import com.jd.pims.pem.service.impl.LoginServiceImpl;
import com.jd.pims.pem.service.impl.LogoutServiceImpl;
import com.jd.pims.pem.util.changeTool;

@Controller
@RequestMapping("/as")
public class BizController {
	@Autowired
	private BizServiceImpl bizServiceImpl;
	@Autowired
	private LoginServiceImpl loginServiceImpl;
	@Autowired
	private LogoutServiceImpl logoutServiceImpl;
	@Value("${jsis.address}")  
    private String jsisAddress;

	/**
	 * 业务接口
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/biz", method = RequestMethod.POST)
	@ResponseBody
	public String login(HttpServletRequest request){
		System.out.println(bizServiceImpl.pim(request));
//		System.out.println(bizService.login("admin", "123"));
		String usr= request.getParameter("usr");
		String pwd= request.getParameter("pwd");
		String returnCode = "1";
		String message = "";
		String result = "";
		if("admin".equals(usr)&&"123456".equals(pwd)){
			return "success";
		}
		try {
			URL url = new URL(jsisAddress+"jdemms?verifyAccount.action");
			HttpURLConnection huconn = (HttpURLConnection)url.openConnection();
			huconn.setDoInput(true);
			huconn.setDoOutput(true);
			huconn.setUseCaches(false);
			huconn.setRequestMethod("POST");
			huconn.setConnectTimeout(30000); 
			huconn.setReadTimeout(30000);
			huconn.setRequestProperty("Content-type", "application/x-java-serialized-object");   
			//huconn.connect();
			OutputStream outStrm = huconn.getOutputStream(); 
			ObjectOutputStream objOutputStrm = new ObjectOutputStream(outStrm); 
			objOutputStrm.writeObject(new String []{usr,pwd});
			objOutputStrm.flush();
			InputStream ips = huconn.getInputStream();
			String a = changeTool.ConvertStream2Json(ips);
			returnCode = (String)JSON.parseObject(a).get("returnCode");
			message = (String)JSON.parseObject(a).get("message");
			objOutputStrm.close(); 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "fail";
	}
	public String getJsifAddress() {
		return jsisAddress;
	}
	public void setJsifAddress(String jsisAddress) {
		this.jsisAddress = jsisAddress;
	}
}
