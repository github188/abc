package com.jd.pims.user.controller;

import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.google.gson.Gson;
import com.jd.pims.comm.BaseController;
import com.jd.pims.comm.PIMSException;
import com.jd.pims.pem.service.impl.BizServiceImpl;
import com.jd.pims.user.model.Employee;
import com.jd.pims.user.service.IUserService;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

	@Resource
	private IUserService userService;
	
	private static final Logger logger = Logger.getLogger(UserController.class
			.getName());

	/**
	 * 用户登录
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public String login(HttpServletRequest request) {
		try {
			String account = request.getParameter("account");
			String password = request.getParameter("password");

			Employee emp = userService.login(account, password);

			// InputStream ins = request.getInputStream();
			// String a = StringUtil.ConvertStream2Json(ins);

			return this.buildSuccessResponse(emp).toString();

		} catch (PIMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return this.buildFailResponse(e.getCode(), e.getMessage())
					.toString();
		}
	}

	/**
	 * 用户登出
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	@ResponseBody
	public String logout(HttpServletRequest request) {
		String empId = request.getParameter("empId");
		userService.logout(empId);
		return this
				.buildFailResponse(SUCESS_RETURN_COCE, SUCESS_RETURN_MESSAGE)
				.toString();

	}
	
	/**
	 * @param file
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/fileUpload", method = RequestMethod.POST)
	public String fileUpload(@RequestParam("file") CommonsMultipartFile file) throws IOException {

		return userService.createAccount(file.getInputStream());
		
	}
	
	
	@RequestMapping(value = "/changePassword", method = RequestMethod.POST)
	@ResponseBody
	public String changePassword(HttpServletRequest request) {
		try {
			String empId = request.getParameter("empId");
			String oldPwd = request.getParameter("oldPwd");
			String newPwd = request.getParameter("newPwd");
			String confirmPwd = request.getParameter("confirmPwd");
			logger.info("empId  "+empId+"  oldPwd  "+oldPwd+"  newPwd  "+newPwd+"  confirmPwd  "+confirmPwd);
			
			userService.changePassword(empId,oldPwd,newPwd,confirmPwd);
			return this
					.buildFailResponse(SUCESS_RETURN_COCE, SUCESS_RETURN_MESSAGE)
					.toString();
		} catch (PIMSException e) {
			return this
					.buildFailResponse(e.getCode(), e.getMessage())
					.toString();
		}

	}
	
	/**
	 * 用户登出
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getUserInfo", method = RequestMethod.POST)
	@ResponseBody
	public String getUserInfo(HttpServletRequest request) {
		String empName = request.getParameter("empName");
		Map<String, Object> uerInfo = userService.getUserInfo(empName);
		Gson gson = new Gson();
		return gson.toJson(uerInfo);

	}
	
}