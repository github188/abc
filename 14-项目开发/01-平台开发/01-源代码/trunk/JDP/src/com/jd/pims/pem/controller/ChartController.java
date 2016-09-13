package com.jd.pims.pem.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.jd.pims.comm.BaseController;
import com.jd.pims.pem.service.IChartService;

@Controller
@RequestMapping("/chart")
public class ChartController extends BaseController {
	@Autowired
	private IChartService chartService;

	/**
	 * 取在岗人数业务接口
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getMapData", method = RequestMethod.POST)
	@ResponseBody
	public String getMapData(HttpServletRequest request,HttpServletResponse response) {
		String id = request.getParameter("id");
		List<Map<String, Object>> list = chartService.getNumberOnDuty(id);
		Gson gson = new Gson();
		return gson.toJson(list);
	}
        

	/**
	 * 业务接口
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getBarData", method = RequestMethod.POST)
	@ResponseBody
	public String getBarData(HttpServletRequest request,HttpServletResponse response) {
		String id = request.getParameter("id");
		List<Map<String, Object>> list=chartService.getEfficiencyHistory(id);
		Gson gson = new Gson();
		return gson.toJson(list);
	}
	/**
	 * 业务接口
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getBar1Data", method = RequestMethod.POST)
	@ResponseBody
	public String getBar1Data(HttpServletRequest request,HttpServletResponse response) {
		String id = request.getParameter("id");
		List<Map<String, Object>> list=chartService.getNumberHistory(id);
		Gson gson = new Gson();
		return gson.toJson(list);
	}
	/**
	 * 业务接口
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getBar2Data", method = RequestMethod.POST)
	@ResponseBody
	public String getBar2Data(HttpServletRequest request,HttpServletResponse response) {
		String id = request.getParameter("id");
		List<Map<String, Object>> list=chartService.getEfficience(id);
		Gson gson = new Gson();
		return gson.toJson(list);
	}


}