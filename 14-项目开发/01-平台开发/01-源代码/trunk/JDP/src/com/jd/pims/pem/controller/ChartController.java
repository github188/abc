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

}
