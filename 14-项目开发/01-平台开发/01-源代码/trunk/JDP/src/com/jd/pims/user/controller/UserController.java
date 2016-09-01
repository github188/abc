package com.jd.pims.user.controller;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jd.pims.comm.BaseController;
import com.jd.pims.util.StringUtil;


@Controller
@RequestMapping("/user")
public class UserController extends BaseController{
	/**
	 * 用户登录 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public String login(HttpServletRequest request){
		String retMsg=null;
		try {
			InputStream ins = request.getInputStream();
			String a = StringUtil.ConvertStream2Json(ins);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "SUCCESS";
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
