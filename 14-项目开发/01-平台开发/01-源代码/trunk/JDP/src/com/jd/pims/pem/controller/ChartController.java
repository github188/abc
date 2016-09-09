package com.jd.pims.pem.controller;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.jd.pims.comm.BaseController;
import com.jd.pims.pem.model.LabourOndutyState;
import com.jd.pims.pem.service.IBizService;
import com.jd.pims.user.model.ControlUnit;
import com.jd.pims.user.service.IUserService;

@Controller
@RequestMapping("/chart")
public class ChartController extends BaseController {
	@Autowired
	private IBizService pemService;
	@Autowired
	private IUserService uesrService;

	/**
	 * 业务接口
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getMapData", method = RequestMethod.POST)
	@ResponseBody
	public void getMapData(HttpServletRequest request,
			HttpServletResponse response) {
		String type= request.getParameter("type");
		String cuid= request.getParameter("cuid");
	}
	/**
	 * 业务接口
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getBarData", method = RequestMethod.POST)
	@ResponseBody
	public void getBarData(HttpServletRequest request,
			HttpServletResponse response) {
		String type= request.getParameter("type");
		String cuid= request.getParameter("cuid");
	}
	/**
	 * 业务接口
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getBar1Data", method = RequestMethod.POST)
	@ResponseBody
	public void getBar1Data(HttpServletRequest request,
			HttpServletResponse response) {
		String type= request.getParameter("type");
		String cuid= request.getParameter("cuid");
	}
	/**
	 * 业务接口
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getBar2Data", method = RequestMethod.POST)
	@ResponseBody
	public void getBar2Data(HttpServletRequest request,
			HttpServletResponse response) {
		String type= request.getParameter("type");
		String cuid= request.getParameter("cuid");
	}
	/**
	 * 业务接口
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getEffectData", method = RequestMethod.POST)
	@ResponseBody
	public void getEffectData(HttpServletRequest request,
			HttpServletResponse response) {
		String type= request.getParameter("type");
		String cuid= request.getParameter("cuid");
	}

}
