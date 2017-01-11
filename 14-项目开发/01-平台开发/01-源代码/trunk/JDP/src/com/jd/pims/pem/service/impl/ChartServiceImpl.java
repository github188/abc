/**
 * 
 */
package com.jd.pims.pem.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jd.pims.pem.dao.LabourEfficiencyDao;
import com.jd.pims.pem.dao.LabourOndutyDao;
import com.jd.pims.pem.model.LabourOnduty;
import com.jd.pims.pem.service.IChartService;
import com.jd.pims.user.dao.UserDao;

/**
 * @author Administrator
 *
 */
@Service("chartServiceImpl")
public class ChartServiceImpl implements IChartService {

	@Autowired
	private LabourEfficiencyDao labourEfficiencyDao;
	@Autowired
	private LabourOndutyDao labourOndutyDao;
	@Autowired
	private UserDao userDao;
	private static final Logger logger = Logger
			.getLogger(ChartServiceImpl.class.getName());

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	@Override
	public List<Map<String, Object>> getNumberOnDuty(String name, Date bizDate) {
		// TODO Auto-generated method stub
		if ("全国".equals(name)) {
			name = "京东集团";
		}
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(bizDate);
		// int hour = calendar.get(Calendar.HOUR_OF_DAY);
		// String temp_str=sdf.format(calendar.getTime());
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String end = df.format(calendar.getTime());
		calendar.add(Calendar.MINUTE, -30);
		String begin = df.format(calendar.getTime());
		// if(hour==0){begin="00:00:00";}
		List<Map<String, Object>> arealist = userDao
				.getCurrentTimeAreaForChart(name);
		for (Map<String, Object> map : arealist) {
			Map<String, Object> tempMap = new HashMap<String, Object>();
			tempMap.put("name", map.get("name"));
			tempMap.put("x", map.get("x"));
			tempMap.put("y", map.get("y"));
			tempMap.put("level", map.get("level"));
			double EmpNum = 0;
			double NotEmpNum = 0;
			double otherNumEmp = 0;
			List<Map<String, Object>> type1List = new ArrayList<Map<String, Object>>();
			List<Map<String, Object>> type2List = new ArrayList<Map<String, Object>>();
			List<Map<String, Object>> type3List = new ArrayList<Map<String, Object>>();
			List<Map<String, Object>> type4List = new ArrayList<Map<String, Object>>();
			List<Map<String, Object>> type5List = new ArrayList<Map<String, Object>>();
			List<Map<String, Object>> labourOndutylist = labourOndutyDao
					.getCurrentTimeLabourOndutyForChart1(begin, end,
							map.get("name").toString());
			if (null != labourOndutylist && !labourOndutylist.isEmpty()
					&& labourOndutylist.size() > 0) {
				for (Map<String, Object> map1 : labourOndutylist) {
					switch (map1.get("personType").toString()) {
					case "1":
						type1List.add(map1);
						break;
					case "2":
						type2List.add(map1);
						break;
					case "3":
						type3List.add(map1);
						break;
					case "4":
						type4List.add(map1);
						break;
					case "5":
						type5List.add(map1);
						break;
					}
				}
				Comparator<Map<String, Object>> rule = new Comparator<Map<String, Object>>() {
					public int compare(Map<String, Object> o1,
							Map<String, Object> o2) {
						String name1 = o1.get("Num").toString();// name1是从你list里面拿出来的一个
						String name2 = o2.get("Num").toString(); // name1是从你list里面拿出来的第二个name
						return name2.compareTo(name1);
					}
				};
				Collections.sort(type1List, rule);
				Collections.sort(type2List, rule);
				Collections.sort(type3List, rule);
				Collections.sort(type4List, rule);
				Collections.sort(type5List, rule);
				EmpNum += Integer
						.parseInt(String.valueOf(type1List.size() == 0 ? 0
								: type1List.get(0).get("Num")));

				NotEmpNum = Integer
						.parseInt(String.valueOf(type4List.size() == 0 ? 0
								: type4List.get(0).get("Num")))
						+ Integer
								.parseInt(String.valueOf(type2List.size() == 0 ? 0
										: type2List.get(0).get("Num")))

						+ Integer
								.parseInt(String.valueOf(type3List.size() == 0 ? 0
										: type3List.get(0).get("Num")));
				otherNumEmp = Integer
						.parseInt(String.valueOf(type5List.size() == 0 ? 0
								: type5List.get(0).get("Num")));
			}
			tempMap.put("EmpNum", EmpNum);
			tempMap.put("NotEmpNum", NotEmpNum);
			tempMap.put("otherNumEmp", otherNumEmp);
			result.add(tempMap);
		}
		return result;
	}

	@Override
	public List<Map<String, Object>> getNumberHistory(String name) {
		// TODO Auto-generated method stub
		if ("全国".equals(name)) {
			name = "京东集团";
		}
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		// cal.set(Calendar.DATE, cal.get(Calendar.DATE) - 1);
		Date startDate = cal.getTime();
		String starttime = sdf.format(startDate);
		cal.add(Calendar.DATE, -7);
		Date endDate = cal.getTime();
		String endtime = sdf.format(endDate);
		list = labourOndutyDao.getHistoryLabourOndutyForChart(endtime,
				starttime, name);
		return getData(list, "LabourOndutyHistory");
	}

	@Override
	public List<Map<String, Object>> getEfficience(String name) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		int timePeriod = 0;
		String time = sdf.format(new Date());
		try {
			// 如果timePeriod为空，默认当前一小时
			// Date d = sdf.parse(time);
			Calendar currentTime = Calendar.getInstance();
			currentTime.setTime(new Date());
			timePeriod = currentTime.get(Calendar.HOUR_OF_DAY);
			if (timePeriod <= 0) {
				timePeriod = 24;
			}
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:00:00");
			currentTime.set(Calendar.HOUR_OF_DAY,
					currentTime.get(Calendar.HOUR_OF_DAY));
			String end = df.format(currentTime.getTime());
			currentTime.add(Calendar.HOUR_OF_DAY, -1);
			String begin = df.format(currentTime.getTime());

			List<Map<String, Object>> areaList = userDao.getAreaList(name);
			for (Map<String, Object> map : areaList) {
				double EmpNum = 0;
				double NotEmpNum = 0;
				double otherNumEmp = 0;
				int order = 0;
				int totalOrder = 0;
				Map<String, Object> tempMap = new HashMap<String, Object>();
				tempMap.put("name", map.get("name"));
				/*
				 * List<Map<String,Object>>
				 * arealist=userDao.getCurrentTimeAreaForChart(name);
				 */
				List<Map<String, Object>> type1List = new ArrayList<Map<String, Object>>();
				List<Map<String, Object>> type2List = new ArrayList<Map<String, Object>>();
				List<Map<String, Object>> type3List = new ArrayList<Map<String, Object>>();
				List<Map<String, Object>> type4List = new ArrayList<Map<String, Object>>();
				List<Map<String, Object>> type5List = new ArrayList<Map<String, Object>>();
				List<Map<String, Object>> labourOndutylist = labourOndutyDao
						.getCurrentTimeLabourOndutyForChart1(begin, end, map
								.get("name").toString());
				if (null != labourOndutylist && !labourOndutylist.isEmpty()
						&& labourOndutylist.size() > 0) {
					for (Map<String, Object> map1 : labourOndutylist) {
						switch (map1.get("personType").toString()) {
						case "1":
							type1List.add(map1);
							break;
						case "2":
							type2List.add(map1);
							break;
						case "3":
							type3List.add(map1);
							break;
						case "4":
							type4List.add(map1);
							break;
						case "5":
							type5List.add(map1);
							break;
						}
					}
					Comparator<Map<String, Object>> rule = new Comparator<Map<String, Object>>() {
						public int compare(Map<String, Object> o1,
								Map<String, Object> o2) {
							String name1 = o1.get("Num").toString();// name1是从你list里面拿出来的一个
							String name2 = o2.get("Num").toString(); // name1是从你list里面拿出来的第二个name
							return name2.compareTo(name1);
						}
					};
					Collections.sort(type1List, rule);
					Collections.sort(type2List, rule);
					Collections.sort(type3List, rule);
					Collections.sort(type4List, rule);
					Collections.sort(type5List, rule);
					EmpNum += Integer
							.parseInt(String.valueOf(type1List.size() == 0 ? 0
									: type1List.get(0).get("Num")));

					NotEmpNum = Integer.parseInt(String.valueOf(type4List
							.size() == 0 ? 0 : type4List.get(0).get("Num")))
							+ Integer
									.parseInt(String
											.valueOf(type2List.size() == 0 ? 0
													: type2List.get(0).get(
															"Num")))
							+ Integer
									.parseInt(String
											.valueOf(type3List.size() == 0 ? 0
													: type3List.get(0).get(
															"Num")));
					otherNumEmp = Integer.parseInt(String.valueOf(type5List
							.size() == 0 ? 0 : type5List.get(0).get("Num")));
				}
				List<Map<String, Object>> totalOrderlist = labourEfficiencyDao
						.getTodayOrderForChart(time, map.get("name").toString());
				if (null != totalOrderlist && !totalOrderlist.isEmpty()
						&& totalOrderlist.size() > 0) {
					for (Map<String, Object> totalOrderMap : totalOrderlist) {
						totalOrder += Integer.parseInt(totalOrderMap.get(
								"orderNum").toString());
					}
				}

				List<Map<String, Object>> orderlist = labourEfficiencyDao
						.getEfficiencyOrderForChart(time, timePeriod,
								map.get("name").toString());
				if (null != orderlist && !orderlist.isEmpty()
						&& orderlist.size() > 0) {
					for (Map<String, Object> orderMap : orderlist) {
						order += Float.parseFloat(orderMap.get("orderNum")
								.toString());
					}
				}
				tempMap.put("EmpNum", EmpNum);
				tempMap.put("NotEmpNum", NotEmpNum);
				tempMap.put("otherNumEmp", otherNumEmp);
				tempMap.put("orderNum", order);
				tempMap.put("totalOrder", totalOrder);
				list.add(tempMap);
			}
		} catch (Exception e) {
			logger.debug("日期解释错误：", e);
			return null;
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> getEfficiencyHistory(String name) {
		// TODO Auto-generated method stub
		if ("全国".equals(name)) {
			name = "京东集团";
		}
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		// cal.set(Calendar.DATE, cal.get(Calendar.DATE) - 1);
		Date startDate = cal.getTime();
		String starttime = sdf.format(startDate);
		cal.add(Calendar.DATE, -7);
		Date endDate = cal.getTime();
		String endtime = sdf.format(endDate);
		List<Map<String, Object>> list = labourEfficiencyDao
				.getHistoryEfficiencyForChart(endtime, starttime, 24, name);
		List<Map<String, Object>> ClerkList = labourEfficiencyDao
				.getHistoryEfficiencyClerkForChart(endtime, starttime, 24, name);
		List<Map<String, Object>> OrderList = labourEfficiencyDao
				.getHistoryEfficiencyOrderForChart(endtime, starttime, 24, name);
		if (null != list && !list.isEmpty() && list.size() > 0) {
			for (Map<String, Object> map : list) {
				Map<String, Object> tempMap = new HashMap<String, Object>();
				tempMap.put("name", map.get("name"));
				tempMap.put("effect", map.get("effect"));
				if (null != OrderList && !OrderList.isEmpty()
						&& OrderList.size() > 0) {
					for (Map<String, Object> orderMap : OrderList) {
						if (map.get("name").equals(orderMap.get("name"))) {
							tempMap.put("orderNum", orderMap.get("orderNum"));
						}
					}
				} else {
					tempMap.put("orderNum", 0);
				}
				if (null != ClerkList && !ClerkList.isEmpty()
						&& ClerkList.size() > 0) {
					for (Map<String, Object> clerkMap : ClerkList) {
						if (map.get("name").equals(clerkMap.get("name"))) {
							tempMap.put("clerkNum", clerkMap.get("clerkNum"));
						}
					}
				} else {
					tempMap.put("clerkNum", 0);
				}
				result.add(tempMap);
			}

		}
		return getData(result, "EfficiencyHistory");
	}

	public List<Map<String, Object>> getData(List<Map<String, Object>> list,
			String type) {
		Calendar cal = Calendar.getInstance();
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		cal.add(Calendar.DATE, -8);
		for (int i = 1; i <= 7; i++) {
			cal.add(Calendar.DATE, 1);
			String month = (cal.get(Calendar.MONTH) + 1) < 10 ? ("0"
					+ (cal.get(Calendar.MONTH) + 1) + "月") : ((cal
					.get(Calendar.MONTH) + 1) + "月");
			String day = cal.get(Calendar.DAY_OF_MONTH) < 10 ? ("0"
					+ cal.get(Calendar.DAY_OF_MONTH) + "日") : (cal
					.get(Calendar.DAY_OF_MONTH) + "日");
			String date = month + day;
			boolean flag = false;
			if (null != list && !list.isEmpty() && list.size() > 0) {
				for (Map<String, Object> map : list) {
					if (map.get("name").equals(date)) {
						result.add(map);
						flag = true;
						break;
					}
				}
			}
			if (!flag) {
				if ("EfficiencyHistory".equals(type)) {
					Map<String, Object> temp = new HashMap<String, Object>();
					temp.put("name", date);
					temp.put("effect", 0);
					temp.put("orderNum", 0);
					temp.put("clerkNum", 0);
					result.add(temp);
				} else {
					Map<String, Object> temp = new HashMap<String, Object>();
					temp.put("name", date);
					temp.put("EmpNum", 0);
					temp.put("NotEmpNum", 0);
					result.add(temp);
				}

			}
		}
		return result;
	}

	@Override
	public List<Map<String, Object>> getMyCenterData(String cuName, Date today) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new LinkedList<Map<String, Object>>();
		// 取当时小时之前每一小时的在岗人数
		String bizDate = sdf.format(today);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(today);
		int currentTime = calendar.get(Calendar.HOUR_OF_DAY);
		if ("京东集团".equals(cuName)) {
			list = getAllCenterData(bizDate, currentTime, cuName);
		} else {
			list = getSingleCenterData(bizDate, currentTime, cuName);
		}
		// 补充数据,前端补
		// patchForWholeDate(list);
		return list;
	}

	private void patchForWholeDate(List<Map<String, Object>> list) {
		for (int i = list.size(); i < 24; i++) {
			Map<String, Object> map = createEmptyHourData(i + 1);
			list.add(map);
		}
	}

	private List<Map<String, Object>> getAllCenterData(String bizDate,
			int currentTime, String cuName) {
		List<Map<String, Object>> arealist = userDao
				.getCurrentTimeAreaForChart(cuName);
		List<Map<String, Object>> resultList = new LinkedList<Map<String, Object>>();
		for (Map<String, Object> areaMap : arealist) {
			List<Map<String, Object>> list = new LinkedList<Map<String, Object>>();
			Set<Integer> allTimes = new HashSet<Integer>();
			for (int i = 1; i < currentTime; i++) {
				allTimes.add(i);
			}
			// TODO Auto-generated method stub
			List<LabourOnduty> listBefore = labourOndutyDao
					.getTodayLabourOndutyForEDC(bizDate, currentTime, areaMap
							.get("name").toString());
			if (listBefore != null) {
				int time = 1;
				Map<String, Object> map = new HashMap<String, Object>();
				int notClerkNum = 0;
				for (LabourOnduty lab : listBefore) {
					if (Integer.parseInt(lab.getHour().toString()) == time) {
						if (lab.getPersonType().equals("1")) {// 正式工
							map.put("clerkNum", lab.getQuantityOnduty());
						} else if (lab.getPersonType().equals("5")) {// 其他
							map.put("otherClerkNum", lab.getQuantityOnduty());
						} else {// 非正式工
							notClerkNum += lab.getQuantityOnduty();
						}
					} else {

						if (time > 0) {
							map.put("time", time);
							map.put("notClerkNum", notClerkNum);
							list.add(map);
							allTimes.remove(time);
						}
						notClerkNum = 0;
						time++;
						map = new HashMap<String, Object>();
						if (lab.getPersonType().equals("1")) {// 正式工
							map.put("clerkNum", lab.getQuantityOnduty());
						} else if (lab.getPersonType().equals("5")) {// 其他
							map.put("otherClerkNum", lab.getQuantityOnduty());
						} else {// 非正式工
							notClerkNum += lab.getQuantityOnduty();
						}
					}
				}
			}
			// 增补空记录
			if (!allTimes.isEmpty()) {
				Iterator<Integer> it = allTimes.iterator();
				while (it.hasNext()) {
					list.add(createEmptyHourData(it.next()));
				}
			}
			// 取当前小时内的在岗人数
			List<LabourOnduty> listCurrent = labourOndutyDao
					.getCurrentTimeLabourOndutyForEDC(bizDate, currentTime,
							cuName);
			Map<String, Object> map = new HashMap<String, Object>();
			int notClerkNum = 0;
			int clerkNum = 0;
			int otherClerkNum = 0;
			if (listCurrent != null) {
				for (LabourOnduty lab : listCurrent) {

					if (lab.getPersonType().equals("1")) {// 正式工
						clerkNum += lab.getQuantityOnduty();
					} else if (lab.getPersonType().equals("5")) {// 其他
						otherClerkNum += lab.getQuantityOnduty();
					} else {// 非正式工
						notClerkNum += lab.getQuantityOnduty();
					}

				}
				map.put("time", currentTime);
				map.put("clerkNum", clerkNum);
				map.put("otherClerkNum", otherClerkNum);
				map.put("notClerkNum", notClerkNum);
				list.add(map);
			} else {// 如果没有返回记录，默认为0
				list.add(createEmptyHourData(currentTime));
			}
			if (resultList.isEmpty()) {
				resultList.addAll(list);
			} else {
				for (Map<String, Object> resultMap : resultList) {
					for (Map<String, Object> singleMap : list) {
						if (resultMap.get("time").equals(singleMap.get("time"))) {
							resultMap
									.put("clerkNum",
											Integer.parseInt(resultMap
													.get("clerkNum") == null ? "0"
													: resultMap.get("clerkNum")
															.toString())
													+ Integer.parseInt(singleMap
															.get("clerkNum") == null ? "0"
															: singleMap.get(
																	"clerkNum")
																	.toString()));
							resultMap
									.put("otherClerkNum",
											Integer.parseInt(resultMap
													.get("otherClerkNum") == null ? "0"
													: resultMap.get(
															"otherClerkNum")
															.toString())
													+ Integer.parseInt(singleMap
															.get("otherClerkNum") == null ? "0"
															: singleMap
																	.get("otherClerkNum")
																	.toString()));
							resultMap
									.put("notClerkNum",
											Integer.parseInt(resultMap
													.get("notClerkNum") == null ? "0"
													: resultMap.get(
															"notClerkNum")
															.toString())
													+ Integer.parseInt(singleMap
															.get("notClerkNum") == null ? "0"
															: singleMap
																	.get("notClerkNum")
																	.toString()));
						}
					}
				}
			}
		}
		return resultList;

	}

	private List<Map<String, Object>> getSingleCenterData(String bizDate,
			int currentTime, String cuName) {
		List<Map<String, Object>> list = new LinkedList<Map<String, Object>>();

		Set<Integer> allTimes = new HashSet<Integer>();
		for (int i = 1; i < currentTime; i++) {
			allTimes.add(i);
		}
		// TODO Auto-generated method stub
		List<LabourOnduty> listBefore = labourOndutyDao
				.getTodayLabourOndutyForEDC(bizDate, currentTime, cuName);
		if (listBefore != null) {
			int time = 1;
			Map<String, Object> map = new HashMap<String, Object>();
			int notClerkNum = 0;
			for (LabourOnduty lab : listBefore) {
				if (Integer.parseInt(lab.getHour().toString()) == time) {
					if (lab.getPersonType().equals("1")) {// 正式工
						map.put("clerkNum", lab.getQuantityOnduty());
					} else if (lab.getPersonType().equals("5")) {// 其他
						map.put("otherClerkNum", lab.getQuantityOnduty());
					} else {// 非正式工
						notClerkNum += lab.getQuantityOnduty();
					}
				} else {

					if (time > 0) {
						map.put("time", time);
						map.put("notClerkNum", notClerkNum);
						list.add(map);
						allTimes.remove(time);
					}
					notClerkNum = 0;
					time++;
					map = new HashMap<String, Object>();
					if (lab.getPersonType().equals("1")) {// 正式工
						map.put("clerkNum", lab.getQuantityOnduty());
					} else if (lab.getPersonType().equals("5")) {// 其他
						map.put("otherClerkNum", lab.getQuantityOnduty());
					} else {// 非正式工
						notClerkNum += lab.getQuantityOnduty();
					}
				}
			}
		}
		// 增补空记录
		if (!allTimes.isEmpty()) {
			Iterator<Integer> it = allTimes.iterator();
			while (it.hasNext()) {
				list.add(createEmptyHourData(it.next()));
			}
		}
		// 取当前小时内的在岗人数
		List<LabourOnduty> listCurrent = labourOndutyDao
				.getCurrentTimeLabourOndutyForEDC(bizDate, currentTime, cuName);
		Map<String, Object> map = new HashMap<String, Object>();
		int notClerkNum = 0;
		int clerkNum = 0;
		int otherClerkNum = 0;
		if (listCurrent != null) {
			for (LabourOnduty lab : listCurrent) {

				if (lab.getPersonType().equals("1")) {// 正式工
					clerkNum += lab.getQuantityOnduty();
				} else if (lab.getPersonType().equals("5")) {// 其他
					otherClerkNum += lab.getQuantityOnduty();
				} else {// 非正式工
					notClerkNum += lab.getQuantityOnduty();
				}

			}
			map.put("time", currentTime);
			map.put("clerkNum", clerkNum);
			map.put("otherClerkNum", otherClerkNum);
			map.put("notClerkNum", notClerkNum);
			list.add(map);
		} else {// 如果没有返回记录，默认为0

			list.add(createEmptyHourData(currentTime));
		}

		return list;
	}

	private Map<String, Object> createEmptyHourData(Integer hour) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("time", hour);
		map.put("clerkNum", 0);
		map.put("otherClerkNum", 0);
		map.put("notClerkNum", 0);
		return map;
	}
}
