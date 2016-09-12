package com.jd.pims.pem.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.jd.pims.pem.model.LabourEfficiency;
import com.jd.pims.pem.model.LabourOndutyDayState;
import com.jd.pims.pem.model.LabourOndutyState;
import com.jd.pims.pem.service.IBizService;
import com.jd.pims.user.model.ControlUnit;
import com.jd.pims.user.model.Employee;
import com.jd.pims.user.service.IUserService;

@Controller
@RequestMapping("/app")
public class AppController extends BaseController {
	@Autowired
	private IBizService pemService;
	@Autowired
	private IUserService uesrService;

	private SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd");
	/**
	 * 业务接口
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getNumberOnDuty", method = RequestMethod.POST)
	@ResponseBody
	public String getNumberOnDuty(HttpServletRequest request,
			HttpServletResponse response) {
		String cuId = request.getParameter("cuId");
		if (cuId != null && !"".equals(cuId)) {
			LabourOndutyState currentState = pemService
					.getNumberOnDuty(cuId);
			JsonObject result=currentState.toJsonObject();
			List<ControlUnit> controlUnits=uesrService.getSubOrganizations(cuId);
			if(controlUnits.size()>0){
				JsonArray subItems = new JsonArray();
				for(ControlUnit cu:controlUnits){
					LabourOndutyState state=pemService.getNumberOnDuty(cu.getId()); 
					subItems.add(state.toJsonObject());
				}
				result.add("subItems", subItems);
			}
			
			return this.buildSuccessResponse(result).toString();
		}
		return this.buildFailResponse(1, "参数不能为空").toString();

		
	}

	/**
	 * 业务接口
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getEfficiency", method = RequestMethod.POST)
	@ResponseBody
	public String getEfficiency(HttpServletRequest request,
			HttpServletResponse response) {
		
		String cuId = request.getParameter("cuId");
		
		if (cuId != null && !"".equals(cuId)) {
			//取上一个小时
			LabourEfficiency parent=pemService.getTimePeriodEfficience(cuId, new Date(), null);
			JsonObject result=parent.toJsonObject();
			List<ControlUnit> controlUnits=uesrService.getSubOrganizations(cuId);
			if(controlUnits.size()>0){
				JsonArray subItems = new JsonArray();
				for(ControlUnit cu:controlUnits){
					LabourEfficiency state=pemService.getTimePeriodEfficience(cu.getId(),new Date(), null); 
					subItems.add(state.toJsonObject());
				}
				result.add("subItems", subItems);
			}
			return this.buildSuccessResponse(result).toString();
		}
		return this.buildFailResponse(1, "参数不能为空").toString();
		
		
	}

	/**
	 * 业务接口
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getNumberHistory", method = RequestMethod.POST)
	@ResponseBody
	public String getNumberHistory(HttpServletRequest request,
			HttpServletResponse response) {
		
		String cuId = request.getParameter("cuId");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String interval = request.getParameter("interval");
		if(interval==null){
			interval="D";
		}else if(!(interval.equals("H") || interval.equals("D"))){
			return this.buildFailResponse(2, "参数值不被支持:interval的值只能是H或者D").toString();
		}
		if (cuId != null && !"".equals(cuId)) {
			
			try {
				List<LabourOndutyDayState> results=pemService.getNumberHistory(cuId, sFormat.parse(startDate), sFormat.parse(endDate), "D");
				return this.buildSuccessResponse(results.toArray(new LabourOndutyDayState[results.size()])).toString();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return this.buildFailResponse(2, "参数值不被支持：startDate或者endDate的日期格式有误！").toString();
			}
			
		}
		
		return this.buildFailResponse(1, "参数不能为空").toString();
	}

	/**
	 * 业务接口
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getEfficiencyHistory", method = RequestMethod.POST)
	@ResponseBody
	public String getEfficiencyHistory(HttpServletRequest request,
			HttpServletResponse response) {
		String cuId = request.getParameter("cuId");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		//String interval = request.getParameter("interval");
		
		if (cuId != null && !"".equals(cuId)) {
			try {
				List<LabourEfficiency> results=pemService.getEfficiencyHistory(cuId, sFormat.parse(startDate), sFormat.parse(endDate));
				
				return this.buildSuccessResponse(new LabourEfficiency[results.size()]).toString();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return this.buildFailResponse(2, "参数值不被支持：startDate或者endDate的日期格式有误！").toString();
			}
			
		}
		return this.buildFailResponse(1, "参数不能为空").toString();

	}

	/**
	 * 业务接口
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getOrganization", method = RequestMethod.POST)
	@ResponseBody
	public String getOrganization(HttpServletRequest request,
			HttpServletResponse response) {

		List<ControlUnit> result=uesrService.getOrganizations();
		
		return this.buildSuccessResponse(new ControlUnit[result.size()]).toString();
	}

	/**
	 * 业务接口
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getEmployee", method = RequestMethod.POST)
	@ResponseBody
	public String getEmployee(HttpServletRequest request,
			HttpServletResponse response) {
		String inputStr = request.getParameter("inputString");
		
		List<Employee> result=uesrService.searchEmployee(inputStr);

		return this.buildSuccessResponse(new Employee[result.size()]).toString();
	}

}
