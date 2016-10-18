package com.jd.pims.pem.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
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
import com.jd.pims.pem.model.LabourOndutyState;
import com.jd.pims.pem.service.IBizService;
import com.jd.pims.user.model.ControlUnit;
import com.jd.pims.user.model.Employee;
import com.jd.pims.user.service.IUserService;
import com.jd.pims.util.XMLFormatException;
import com.jd.pims.util.XMLHelper;

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

		// 取指定控制单元上一个小时人效
		LabourEfficiency parent = pemService.getTimePeriodEfficience(cuId,
				new Date(), null);
		if (null != parent) {
			JsonObject result = parent.toJsonObject();
			List<ControlUnit> controlUnits = uesrService
					.getSubOrganizations(cuId);
			//取所有子控制单元上一个小时的人效
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
				//构造返回的数据结构
				result.add("subItems", subItems);
				
				//求指定控制单元的平均人效
				
				double avgEfficiency=parent.getEfficiency()/controlUnits.size();
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
		ControlUnit cu = null;
		if (cuId == null) {
			cu = uesrService.findRootOrganization();
			if (null != cu) {
				cuId = cu.getId();
			}
		} else {
			cu = uesrService.findOrganization(cuId);
		}

		if (startDate != null && !"".equals(startDate) && endDate != null
				&& !"".equals(endDate)) {

			try {
				List<LabourOndutyState> results = pemService.getNumberHistory(
						cuId, sFormat.parse(startDate), sFormat.parse(endDate),
						"D");
				if (null != results  && !results.isEmpty()) {
					// 如果返回的记录数少于7天，自动补全
					LabourOndutyState[] array = new LabourOndutyState[7];
					if (results.size() < 7) {
						Date stDate = sFormat.parse(startDate);
						for (int i = 0; i < 7; i++) {
							LabourOndutyState match = null;
							Calendar currentTime = Calendar.getInstance();
							currentTime.setTime(stDate);
							currentTime.add(Calendar.DAY_OF_MONTH, i);
							
							for (LabourOndutyState state : results) {
								if (state.getDayTime().equals(
										sFormat.format(currentTime.getTime()))) {
									match = state;
									break;
								}
							}
							if (match != null) {
								array[i] = match;
							} else {
								array[i] = new LabourOndutyState();
								array[i].setCuId(cuId);
								array[i].setCuName(cu.getCuName());
								array[i].setDayTime(sFormat.format(currentTime
										.getTime()));
							}
						}
					}
					
					Arrays.sort(array);
					return this.buildSuccessResponse(array).toString();
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
					for (LabourEfficiency le : results) {
						if(le.getNumberOnduty()>0){
							le.setEfficiency(le.getOrderQuantity()/(double)le.getNumberOnduty());
						}

					}
					LabourEfficiency[] arr = results.toArray(
							new LabourEfficiency[results.size()]);
					//补充缺失日期数据
					LabourEfficiency[] array=patchMissEfficiencyHistoryDateData(arr,startDate);
					for(LabourEfficiency le:array){
						le.setCuId(cuId);
						le.setCuName(cu.getCuName());
					}
					Arrays.sort(array);

					return this.buildSuccessResponse(array).toString();
				}
				JsonObject retMsg = new JsonObject();
				retMsg.addProperty("returnCode", 0);
				retMsg.addProperty("message", "无历史在人效数据！");
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
	 * 补全历史人效数据
	 * 
	 * @param arr
	 * @throws ParseException 
	 */
	private LabourEfficiency[] patchMissEfficiencyHistoryDateData(
			LabourEfficiency[] arr,String startDate) throws ParseException {
		if (arr.length == 7) {
			return arr;
		}
		LabourEfficiency[] array=new LabourEfficiency[7];
		Date stDate = sFormat.parse(startDate);
		for (int i = 0; i < 7; i++) {
			LabourEfficiency match = null;
			Calendar currentTime = Calendar.getInstance();
			currentTime.setTime(stDate);
			currentTime.add(Calendar.DAY_OF_MONTH, i);
			for (LabourEfficiency le : arr) {
				if (sFormat.format(le.getBizDate()).equals(sFormat.format(currentTime.getTime()))) {
					match = le;
					break;
				}
			}
			if (match != null) {
				array[i] = match;
			} else {
				array[i] = new LabourEfficiency();
				array[i].setBizDate(currentTime.getTime());
			}
		}
		return array;
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
				parent != null ? parent.getEfficiency() : 0);
		return this.buildSuccessResponse(result).toString();
	}
	
	@RequestMapping(value = "/getUpdateState", method = RequestMethod.POST)
	@ResponseBody
	public String getUpdateState(HttpServletRequest request,
			HttpServletResponse response) {
		String type = request.getParameter("type");
		if(type!=null){
			try {
				XMLHelper xmlHelper=new XMLHelper(type+"-update.xml");
				String lastVersion=xmlHelper.getLastVersion();
				String updateContent=xmlHelper.getUpdateContent(lastVersion);
				String updateUrl=xmlHelper.getUpdateUrl(lastVersion);
				JsonObject result = new JsonObject();
				result.addProperty("lastVersion", lastVersion);
				result.addProperty("updateContent", updateContent);
				result.addProperty("updateUrl", updateUrl);
				this.buildSuccessResponse(result);
			}catch(XMLFormatException xfe){
				return this.buildFailResponse(xfe.getCode(),xfe.getMessage()).toString();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return this.buildFailResponse(-2, "升级文件不存在！").toString();
			}
		}
		return this.buildFailResponse(-1, "请求参数不存在！").toString();
	}
}
