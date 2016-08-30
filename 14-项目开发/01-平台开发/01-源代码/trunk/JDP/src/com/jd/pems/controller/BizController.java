package com.jd.pems.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jd.pems.service.impl.BizServiceImpl;
import com.jd.pems.service.impl.LoginServiceImpl;
import com.jd.pems.service.impl.LogoutServiceImpl;

@Controller
@RequestMapping("/as")
public class BizController {
	@Autowired
	private BizServiceImpl bizServiceImpl;
	@Autowired
	private LoginServiceImpl loginServiceImpl;
	@Autowired
	private LogoutServiceImpl logoutServiceImpl;
	
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
		if("admin".equals(usr)&&"123456".equals(pwd)){
			return "success";
		}
		return "fail";
	}
	
}
