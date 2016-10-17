/**
 * 
 */
package com.jd.pims.pem.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jd.pims.pem.dao.LabourEfficiencyDao;
import com.jd.pims.pem.dao.LabourOndutyDao;
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
	private static final Logger logger = Logger.getLogger(ChartServiceImpl.class
			.getName());
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");   
	@Override
	public List<Map<String,Object>> getNumberOnDuty(String name) {
		// TODO Auto-generated method stub
		if("全国".equals(name)){
			name="京东集团";
		}
		List<Map<String,Object>> result= new ArrayList<Map<String,Object>>();
		Calendar calendar = Calendar.getInstance();
		String temp_str=sdf.format(calendar.getTime()); 
		SimpleDateFormat df = new SimpleDateFormat("HH:00:00");
		calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY));
		String end = df.format(calendar.getTime());
		calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - 1);
		String begin = df.format(calendar.getTime());
		List<Map<String,Object>> arealist=userDao.getCurrentTimeAreaForChart(name);
		for(Map<String,Object>map:arealist){
			Map<String,Object>tempMap = new HashMap<String,Object>();
			tempMap.put("name",map.get("name"));
			tempMap.put("x",map.get("x"));
			tempMap.put("y",map.get("y"));
			List<Map<String,Object>> labourOndutylist=labourOndutyDao.getCurrentTimeLabourOndutyForChart(temp_str,begin,end, map.get("name").toString());
			if(null!=labourOndutylist&&!labourOndutylist.isEmpty()&&labourOndutylist.size()>0){
	            tempMap.put("EmpNum",labourOndutylist.get(0).get("EmpNum"));
	            tempMap.put("NotEmpNum",labourOndutylist.get(0).get("NotEmpNum"));
	            tempMap.put("otherNumEmp",labourOndutylist.get(0).get("otherNumEmp"));
			}else{
	            tempMap.put("EmpNum",0);
	            tempMap.put("NotEmpNum",0);
	            tempMap.put("otherNumEmp",0);
			}
			result.add(tempMap);
		}
			
		return result;
	}

	@Override
	public List<Map<String,Object>> getNumberHistory(String name) {
		// TODO Auto-generated method stub
		if("全国".equals(name)){
			name="京东集团";
		}
		List<Map<String,Object>> list= new ArrayList<Map<String,Object>>();
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());   
		//cal.set(Calendar.DATE, cal.get(Calendar.DATE) - 1);
		Date startDate = cal.getTime();
		String starttime =sdf.format(startDate);
		cal.set(Calendar.DATE, cal.get(Calendar.DATE) - 7);
		Date endDate = cal.getTime();
		String endtime =sdf.format(endDate);
		list=labourOndutyDao.getHistoryLabourOndutyForChart(endtime,starttime, name);	
		return getData(list,"LabourOndutyHistory");
	}

	@Override
	public  List<Map<String,Object>> getEfficience(String name) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list= new ArrayList<Map<String,Object>>();
		int timePeriod = 0;
		String time =sdf.format(new Date());
		try {
			//如果timePeriod为空，默认当前一小时
			//Date d = sdf.parse(time);
			Calendar currentTime = Calendar.getInstance();
			currentTime.setTime(new Date());
			 timePeriod=currentTime.get(Calendar.HOUR_OF_DAY);
			if(timePeriod<0){
				timePeriod=24;
			}
			SimpleDateFormat df = new SimpleDateFormat("HH:00:00");
			currentTime.set(Calendar.HOUR_OF_DAY, currentTime.get(Calendar.HOUR_OF_DAY));
			String end = df.format(currentTime.getTime());
			currentTime.set(Calendar.HOUR_OF_DAY, currentTime.get(Calendar.HOUR_OF_DAY) - 1);
			String begin = df.format(currentTime.getTime());
	        List<Map<String,Object>>areaList = userDao.getAreaList(name);
	        for(Map<String,Object>map:areaList){
		        float EmpNum = 0;
		        float NotEmpNum = 0;
		        float otherNumEmp = 0;
		        int order = 0;
		        Map<String,Object>tempMap = new HashMap<String,Object>();
		        tempMap.put("name",map.get("name"));
			/*	List<Map<String,Object>> arealist=userDao.getCurrentTimeAreaForChart(name);*/
				List<Map<String,Object>> orderlist=labourEfficiencyDao.getEfficiencyOrderForChart(
						time, timePeriod,begin,end,map.get("name").toString());
				if(null!=orderlist&&!orderlist.isEmpty()&&orderlist.size()>0){
					for(Map<String,Object>orderMap:orderlist){
						order+=Integer.parseInt(orderMap.get("orderNum").toString());
					}
				}
				List<Map<String,Object>> clerklist=labourOndutyDao.getCurrentTimeLabourOndutyForChart(
						time, begin,end,map.get("name").toString());
				if(null!=clerklist&&!clerklist.isEmpty()&&clerklist.size()>0){
					for(Map<String,Object>clerkMap:clerklist){
						EmpNum+=Float.parseFloat(clerkMap.get("EmpNum").toString());
						NotEmpNum+=Float.parseFloat(clerkMap.get("NotEmpNum").toString());
						otherNumEmp+=Float.parseFloat(clerkMap.get("otherNumEmp").toString());
					}
				}
	            tempMap.put("EmpNum",EmpNum);
	            tempMap.put("NotEmpNum",NotEmpNum);
	            tempMap.put("otherNumEmp",otherNumEmp);
	            tempMap.put("orderNum",order);
	            list.add(tempMap);	
			}		
		} catch (Exception e) {
			logger.debug("日期解释错误：", e);
			return null;
		}
        return list;
	}

	@Override
	public List<Map<String,Object>> getEfficiencyHistory(String name) {
		// TODO Auto-generated method stub
		if("全国".equals(name)){
			name="京东集团";
		}
		List<Map<String,Object>> result= new ArrayList<Map<String,Object>>();
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());   
		//cal.set(Calendar.DATE, cal.get(Calendar.DATE) - 1);
		Date startDate = cal.getTime();
		String starttime =sdf.format(startDate);
		cal.set(Calendar.DATE, cal.get(Calendar.DATE) - 7);
		Date endDate = cal.getTime();
		String endtime =sdf.format(endDate);
		List<Map<String,Object>> list = labourEfficiencyDao.getHistoryEfficiencyForChart(endtime,starttime,24, name);
		List<Map<String,Object>> OrderList = labourEfficiencyDao.getHistoryEfficiencyOrderForChart(endtime,starttime,24, name);
		if(null!=list&&!list.isEmpty()&&list.size()>0){
			for(Map<String,Object>map:list){
				Map<String,Object>tempMap = new HashMap<String,Object>();
				tempMap.put("name", map.get("name"));
				tempMap.put("effect", map.get("effect"));
				tempMap.put("clerkNum", map.get("clerkNum"));
				if(null!=OrderList&&!OrderList.isEmpty()&&OrderList.size()>0){
					for(Map<String,Object>orderMap:OrderList){
						if(map.get("name").equals(orderMap.get("name"))){
							tempMap.put("orderNum", orderMap.get("orderNum"));
						}
					}
				}else{
					tempMap.put("orderNum",0);
				}
				result.add(tempMap);
			}
			
		}
		return getData(result,"EfficiencyHistory");
	}
	public List<Map<String,Object>> getData(List<Map<String,Object>> list,String type){
		Calendar cal = Calendar.getInstance();
		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
		cal.set(Calendar.DATE, cal.get(Calendar.DATE) - 8);
		for(int i=1;i<=7;i++){
			cal.set(Calendar.DATE, cal.get(Calendar.DATE) + 1);
			String month = (cal.get(Calendar.MONTH)+1)<10?("0"+(cal.get(Calendar.MONTH)+1)+"月"):((cal.get(Calendar.MONTH)+1)+"月");
			String day = cal.get(Calendar.DAY_OF_MONTH)<10?("0"+cal.get(Calendar.DAY_OF_MONTH)+"日"):(cal.get(Calendar.DAY_OF_MONTH)+"日");
			String date = month+day;
			boolean flag = false;
			if(null!=list&&!list.isEmpty()&&list.size()>0){
				for(Map<String,Object>map:list){
					if(map.get("name").equals(date)){
						result.add(map);
						flag = true;
						break;
					}
				}
			}
			if(!flag){
				if("EfficiencyHistory".equals(type)){
					Map<String,Object>temp = new HashMap<String,Object>();
					temp.put("name",date);
					temp.put("effect",0);
					temp.put("orderNum",0);
					temp.put("clerkNum",0);
					result.add(temp);
				}else{
					Map<String,Object>temp = new HashMap<String,Object>();
					temp.put("name",date);
					temp.put("EmpNum",0);
					temp.put("NotEmpNum",0);
					result.add(temp);
				}

			}
		}
		return result;
	}
}
