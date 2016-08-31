package com.jd.pims.pem.controller;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jd.pims.util.changeTool;

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
