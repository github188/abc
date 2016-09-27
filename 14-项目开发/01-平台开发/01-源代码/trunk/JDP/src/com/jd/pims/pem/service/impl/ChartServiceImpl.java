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
		Calendar calendar = Calendar.getInstance();
		String temp_str=sdf.format(calendar.getTime()); 
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:00:00");
		calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY));
		String end = df.format(calendar.getTime());
		calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - 1);
		String begin = df.format(calendar.getTime());
			List<Map<String,Object>> templist=labourOndutyDao.getCurrentTimeLabourOndutyForChart(temp_str,begin,end, name);
			if(null!=templist&&!templist.isEmpty()&&templist.size()>0){
				return templist;
			}else{
				return null;
			}
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
	        List<Map<String,Object>>areaList = userDao.getAreaList(name);
	        for(Map<String,Object>map:areaList){
		        Map<String,Object>tempMap = new HashMap<String,Object>();
		        tempMap.put("name",map.get("name"));
				List<Map<String,Object>> templist=labourEfficiencyDao.getEfficiencyForChart(
						time, timePeriod,map.get("name").toString());
				if(null!=templist&&!templist.isEmpty()&&templist.size()>0){
	                tempMap.put("effect",templist.get(0).get("effect"));
	                tempMap.put("orderNum",templist.get(0).get("orderNum"));
				}else{
	                tempMap.put("effect",0);
	                tempMap.put("orderNum",0);
				}
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
		List<Map<String,Object>> list= new ArrayList<Map<String,Object>>();
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());   
		//cal.set(Calendar.DATE, cal.get(Calendar.DATE) - 1);
		Date startDate = cal.getTime();
		String starttime =sdf.format(startDate);
		cal.set(Calendar.DATE, cal.get(Calendar.DATE) - 7);
		Date endDate = cal.getTime();
		String endtime =sdf.format(endDate);
		list = labourEfficiencyDao.getHistoryEfficiencyForChart(endtime,starttime,24, name);
		return getData(list,"EfficiencyHistory");
	}
	public List<Map<String,Object>> getData(List<Map<String,Object>> list,String type){
		Calendar cal = Calendar.getInstance();
		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
		cal.set(Calendar.DATE, cal.get(Calendar.DATE) - 8);
		for(int i=1;i<=7;i++){
			cal.set(Calendar.DATE, cal.get(Calendar.DATE) + 1);
			String month = (cal.get(Calendar.MONTH)+1)<11?("0"+(cal.get(Calendar.MONTH)+1)+"月"):((cal.get(Calendar.MONTH)+1)+"月");
			String day = cal.get(Calendar.DAY_OF_MONTH)<11?("0"+cal.get(Calendar.DAY_OF_MONTH)+"日"):(cal.get(Calendar.DAY_OF_MONTH)+"日");
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
