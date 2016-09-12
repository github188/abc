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
import com.jd.pims.comm.ControlUnitCache;
import com.jd.pims.pem.model.LabourOndutyState;
import com.jd.pims.pem.service.IBizService;
import com.jd.pims.pem.service.IChartService;
import com.jd.pims.user.model.ControlUnit;
import com.jd.pims.user.service.IUserService;

@Controller
@RequestMapping("/chart")
public class ChartController extends BaseController {
	@Autowired
	private IChartService chartService;
	@Autowired
	private IUserService uesrService;

	/**
	 * 取在岗人数业务接口
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getMapData", method = RequestMethod.POST)
	@ResponseBody
	public List<LabourOndutyState> getMapData(HttpServletRequest request,
			HttpServletResponse response) {
		String cuId = request.getParameter("cuId");
		 List<LabourOndutyState> list = chartService.getNumberOnDuty(cuId);
		return list;
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
		String cuid= request.getParameter("id");
		//chartService.getEfficiencyHistory(cuid);
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
		String cuid= request.getParameter("id");
		//chartService.getNumberHistory(cuid);
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
		String cuid= request.getParameter("id");
		//chartService.getTimePeriodEfficience(cuid);
	}
	/**
	 * 业务接口
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getOrderNumberData", method = RequestMethod.POST)
	@ResponseBody
	public void getOrderNumberData(HttpServletRequest request,
			HttpServletResponse response) {
		String cuid= request.getParameter("id");
	}

}
