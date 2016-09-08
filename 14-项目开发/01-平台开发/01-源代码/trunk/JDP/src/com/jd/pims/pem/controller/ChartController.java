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
@RequestMapping("/app")
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
		String usr = request.getParameter("empId");
		String pwd = request.getParameter("orgCode");

		JsonObject jsonResponse = new JsonObject();
		jsonResponse.addProperty("returnCode", "");
		jsonResponse.addProperty("message", "");

		JsonObject jsonResult = new JsonObject();
		jsonResult.addProperty("orgCode", "");
		jsonResult.addProperty("efficiency", "");

		JsonArray jsonSubItems = new JsonArray();
		JsonObject jsonElement = new JsonObject();
		jsonElement.addProperty("orgCode", "");
		jsonElement.addProperty("efficiency", "");

		jsonSubItems.add(jsonElement);
		jsonResult.add("subItems", jsonSubItems);
		jsonResponse.add("result", jsonResult);

		System.out.println(jsonResponse.toString());

		return jsonResponse.toString();
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
		String usr = request.getParameter("empId");
		String pwd = request.getParameter("orgCode");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String interval = request.getParameter("interval");

		JsonObject jsonResponse = new JsonObject();
		jsonResponse.addProperty("returnCode", "");
		jsonResponse.addProperty("message", "");

		JsonObject jsonResult = new JsonObject();
		jsonResult.addProperty("orgCode", "");
		jsonResult.addProperty("dayTime", "");
		jsonResult.addProperty("numEmp", "");
		jsonResult.addProperty("numTemp", "");
		jsonResult.addProperty("numOther", "");

		jsonResponse.add("result", jsonResult);

		System.out.println(jsonResponse.toString());

		return jsonResponse.toString();
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
		String usr = request.getParameter("empId");
		String pwd = request.getParameter("orgCode");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String interval = request.getParameter("interval");

		JsonObject jsonResponse = new JsonObject();
		jsonResponse.addProperty("returnCode", "");
		jsonResponse.addProperty("message", "");

		JsonObject jsonResult = new JsonObject();
		jsonResult.addProperty("orgCode", "");
		jsonResult.addProperty("dayTime", "");
		jsonResult.addProperty("efficiency", "");

		jsonResponse.add("result", jsonResult);

		System.out.println(jsonResponse.toString());

		return jsonResponse.toString();
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
		String usr = request.getParameter("empId");

		JsonObject jsonResponse = new JsonObject();
		jsonResponse.addProperty("returnCode", "");
		jsonResponse.addProperty("message", "");

		JsonObject jsonResult = new JsonObject();
		jsonResult.addProperty("id", "");
		jsonResult.addProperty("orgCode", "");
		jsonResult.addProperty("orgName", "");
		jsonResult.addProperty("parentId", "");

		jsonResponse.add("result", jsonResult);

		System.out.println(jsonResponse.toString());

		return jsonResponse.toString();
	}

	/**
	 * 业务接口
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/verifyAccount", method = RequestMethod.POST)
	@ResponseBody
	public String verifyAccount(HttpServletRequest request,
			HttpServletResponse response) {
		String account = request.getParameter("account");
		String password = request.getParameter("password");

		JsonObject jsonResponse = new JsonObject();
		jsonResponse.addProperty("returnCode", "");
		jsonResponse.addProperty("message", "");

		JsonObject jsonResult = new JsonObject();
		jsonResult.addProperty("id", "");
		jsonResult.addProperty("empName", "");
		jsonResult.addProperty("sex", "");
		jsonResult.addProperty("orgCode", "");
		jsonResult.addProperty("orgName", "");

		jsonResponse.add("result", jsonResult);

		System.out.println(jsonResponse.toString());

		return jsonResponse.toString();
	}

}
