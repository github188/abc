package com.jd.pims.pem.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		if (cuId == null) {
			ControlUnit root = uesrService.findRootOrganization();
			if (null != root) {
				cuId = root.getId();
			} else {
				JsonObject retMsg = new JsonObject();
				retMsg.addProperty("returnCode", 0);
				retMsg.addProperty("message", "无组织结构数据");
				return retMsg.toString();
			}
		}
		LabourOndutyState currentState = pemService.getNumberOnDuty(cuId);
		JsonObject result = new JsonObject();
		if (null != currentState) {
			result = currentState.toJsonObject();
		} else {
			JsonObject retMsg = new JsonObject();
			retMsg.addProperty("returnCode", 0);
			retMsg.addProperty("message", "无在岗人数统计数据");
			return retMsg.toString();
		}

		List<ControlUnit> controlUnits = uesrService.getSubOrganizations(cuId);
		if (null != controlUnits && controlUnits.size() > 0) {
			JsonArray subItems = new JsonArray();
			for (ControlUnit cu : controlUnits) {
				LabourOndutyState state = pemService
						.getNumberOnDuty(cu.getId());
				if (null != state) {
					subItems.add(state.toJsonObject());
				}
			}
			result.add("subItems", subItems);
			return this.buildSuccessResponse(result).toString();
		}
		JsonObject retMsg = new JsonObject();
		retMsg.addProperty("returnCode", 0);
		retMsg.addProperty("message", "无数据");
		return retMsg.toString();
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
		if (cuId == null) {
			ControlUnit root = uesrService.findRootOrganization();
			if (null != root) {
				cuId = root.getId();
			} else {
				JsonObject retMsg = new JsonObject();
				retMsg.addProperty("returnCode", 0);
				retMsg.addProperty("message", "无组织结构数据");
				return retMsg.toString();
			}
		}

		// 取上一个小时
		LabourEfficiency parent = pemService.getTimePeriodEfficience(cuId,
				new Date(), null);
		if (null != parent) {
			JsonObject result = parent.toJsonObject();
			List<ControlUnit> controlUnits = uesrService
					.getSubOrganizations(cuId);
			if (null != controlUnits && controlUnits.size() > 0) {
				JsonArray subItems = new JsonArray();
				for (ControlUnit cu : controlUnits) {
					LabourEfficiency state = pemService
							.getTimePeriodEfficience(cu.getId(), new Date(),
									null);
					if (null != state) {
						subItems.add(state.toJsonObject());
					}
				}
				result.add("subItems", subItems);
			}
			return this.buildSuccessResponse(result).toString();
		}
		JsonObject retMsg = new JsonObject();
		retMsg.addProperty("returnCode", 0);
		retMsg.addProperty("message", "无实时人效数据");
		return retMsg.toString();

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
		if (interval == null) {
			interval = "D";
		} else if (!(interval.equals("H") || interval.equals("D"))) {
			return this.buildFailResponse(2, "参数值不被支持:interval的值只能是H或者D")
					.toString();
		}
		if (cuId == null) {
			ControlUnit root = uesrService.findRootOrganization();
			if (null != root) {
				cuId = root.getId();
			}
		}

		if (startDate != null && !"".equals(startDate) && endDate != null
				&& !"".equals(endDate)) {

			try {
				List<LabourOndutyState> results = pemService.getNumberHistory(
						cuId, sFormat.parse(startDate), sFormat.parse(endDate),
						"D");
				if (null != results && results.size() > 0 && !results.isEmpty()) {
					LabourOndutyState[] LabourOndutyState = new LabourOndutyState[results
							.size()];
					LabourOndutyState = results.toArray(LabourOndutyState);
					Arrays.sort(LabourOndutyState);
					return this.buildSuccessResponse(LabourOndutyState)
							.toString();
				}
				JsonObject retMsg = new JsonObject();
				retMsg.addProperty("returnCode", 0);
				retMsg.addProperty("message", "无历史在岗人数数据");
				return retMsg.toString();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return this.buildFailResponse(2,
						"参数值不被支持：startDate或者endDate的日期格式有误！").toString();
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
		// String interval = request.getParameter("interval");
		if (cuId == null) {
			ControlUnit root = uesrService.findRootOrganization();
			if (null != root) {
				cuId = root.getId();
			}
		}
		ControlUnit cu = uesrService.findOrganization(cuId);

		if (startDate != null && !"".equals(startDate) && endDate != null
				&& !"".equals(endDate)) {
			try {
				List<LabourEfficiency> results = pemService
						.getEfficiencyHistory(cuId, sFormat.parse(startDate),
								sFormat.parse(endDate));
				if (null != results && !results.isEmpty()) {
					Map<String, LabourEfficiency> map = new HashMap<String, LabourEfficiency>();
					LabourEfficiency nle = null;
					for (LabourEfficiency le : results) {
						if (map.containsKey(sFormat.format(le.getBizDate()))) {
							nle = map.get(sFormat.format(le.getBizDate()));
						} else {
							nle = new LabourEfficiency();
							nle.setCuId(cuId);
							nle.setCuName(cu.getCuName());
							nle.setBizDate(le.getBizDate());
							map.put(sFormat.format(le.getBizDate()), nle);
						}
						Double efficiency = nle.getEfficiency();
						efficiency += le.getEfficiency();
						int numberOnduty = nle.getNumberOnduty()
								+ le.getNumberOnduty();
						int orderQuantity = nle.getOrderQuantity()
								+ le.getOrderQuantity();
						nle.setAvgEfficiency(efficiency / 2);
						nle.setEfficiency(efficiency / 2);
						nle.setNumberOnduty(numberOnduty / 2);
						nle.setOrderQuantity(orderQuantity / 2);

					}
					LabourEfficiency[] arr = map.values().toArray(
							new LabourEfficiency[results.size()]);
					Arrays.sort(arr);
					return this.buildSuccessResponse(arr).toString();
				}
				JsonObject retMsg = new JsonObject();
				retMsg.addProperty("returnCode", 0);
				retMsg.addProperty("message", "无历史在岗人数数据");
				return retMsg.toString();
			} catch (ParseException e) {
				e.printStackTrace();
				return this.buildFailResponse(2,
						"参数值不被支持：startDate或者endDate的日期格式有误！").toString();
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

		List<ControlUnit> result = uesrService.getOrganizations();
		if (null != result && result.size() > 0 && !result.isEmpty()) {
			return this.buildSuccessResponse(
					result.toArray(new ControlUnit[result.size()])).toString();
		}
		JsonObject retMsg = new JsonObject();
		retMsg.addProperty("returnCode", 0);
		retMsg.addProperty("message", "无组织结构数据");
		return retMsg.toString();
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
		String inputStr = request.getParameter("inputStr");

		List<Employee> result = uesrService.searchEmployee(inputStr);
		if (null != result && result.size() > 0 && !result.isEmpty()) {
			return this.buildSuccessResponse(
					result.toArray(new Employee[result.size()])).toString();
		}
		JsonObject retMsg = new JsonObject();
		retMsg.addProperty("returnCode", 1);
		retMsg.addProperty("message", "无匹配的人员数据");
		return retMsg.toString();
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getLabourAndEfficiencyOfGroup", method = RequestMethod.POST)
	@ResponseBody
	public String getLabourAndEfficiencyOfGroup(HttpServletRequest request,
			HttpServletResponse response) {
		ControlUnit root = uesrService.findRootOrganization();

		LabourEfficiency parent = pemService.getTimePeriodEfficience(
				root.getId(), new Date(), null);
		LabourOndutyState currentLabour = pemService.getNumberOnDuty(root
				.getId());
		JsonObject result = new JsonObject();
		int totalLabour = 0;
		if (currentLabour != null) {
			totalLabour = currentLabour.getNumEmp()
					+ currentLabour.getNumTemp() + currentLabour.getNumOther();
		}
		result.addProperty("totalLabour", totalLabour);
		result.addProperty("avgEfficiency",
				parent != null ? parent.getAvgEfficiency() : 0);
		return this.buildSuccessResponse(result).toString();
	}
}
