package com.jd.pims.user.controller;

import java.io.IOException;
import java.io.InputStream;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jd.pims.comm.BaseController;
import com.jd.pims.comm.PIMSException;
import com.jd.pims.user.model.Employee;
import com.jd.pims.user.service.IUserService;
import com.jd.pims.util.StringUtil;


@Controller
@RequestMapping("/user")
public class UserController extends BaseController{
	

	@Resource
	private IUserService userService;
	/**
	 * 用户登录 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public String login(HttpServletRequest request){
		try {
			String account=request.getParameter("account");
			String password=request.getParameter("password");
			
			Employee emp=userService.login(account, password);
			
			//InputStream ins = request.getInputStream();
			//String a = StringUtil.ConvertStream2Json(ins);
		
			return this.buildSuccessResponse(emp);
		
		} catch (PIMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return this.buildFailResponse(e.getCode(), e.getMessage());
		}
	}
	
	/**
	 * 用户登录 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	@ResponseBody
	public String logout(HttpServletRequest request){
		try {
			InputStream ins = request.getInputStream();
			String a = StringUtil.ConvertStream2Json(ins);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "SUCCESS";
	}
}
