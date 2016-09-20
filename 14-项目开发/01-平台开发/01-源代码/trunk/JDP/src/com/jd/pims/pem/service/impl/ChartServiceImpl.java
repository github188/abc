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
		List<Map<String,Object>> list= new ArrayList<Map<String,Object>>();
		Date dt = new Date(); 
		String temp_str=sdf.format(dt);  
			List<Map<String,Object>> templist=labourOndutyDao.getCurrentTimeLabourOndutyForChart(temp_str, name);
			if(null!=templist&&!templist.isEmpty()&&templist.size()>0){
				return list;
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
		return list;
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
			 timePeriod=currentTime.get(Calendar.HOUR_OF_DAY)-1;
			if(timePeriod<0){
				timePeriod=24;
			}
	        List<Map<String,Object>>areaList = userDao.getAreaList(name);
	        for(Map<String,Object>map:areaList){
		        Map<String,Object>tempMap = new HashMap<String,Object>();
		        tempMap.put("name",map.get("name"));
				List<Map<String,Object>> templist=labourEfficiencyDao.getEfficiencyForChart(
						time, timePeriod,name);
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
		return list;
	}

}
