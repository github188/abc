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
import com.jd.pims.pem.dao.OrderQuantityDao;
import com.jd.pims.pem.model.LabourEfficiency;
import com.jd.pims.pem.model.LabourOnduty;
import com.jd.pims.pem.model.LabourOndutyDayState;
import com.jd.pims.pem.model.LabourOndutyState;
import com.jd.pims.pem.service.IBizService;
import com.jd.pims.user.dao.UserDao;
import com.jd.pims.user.model.ControlUnit;

@Service("bizServiceImpl")
public class BizServiceImpl implements IBizService {

	private static final Logger logger = Logger.getLogger(BizServiceImpl.class
			.getName());

	@Autowired
	private LabourEfficiencyDao labourEfficiencyDao;
	@Autowired
	private LabourOndutyDao labourOndutyDao;
	@Autowired
	private OrderQuantityDao orderQuantityDao;
	@Autowired
	private UserDao userDao;

	private SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd");

	@Override
	public LabourOndutyState getNumberOnDuty(String cuId) {
		return this.getNumberOnDuty(cuId, new Date());
	}

	@Override
	public LabourOndutyState getNumberOnDuty(String cuId, Date date) {
		// TODO Auto-generated method stub
		LabourOndutyState state = new LabourOndutyState();
		state.setCuId(cuId);
		ControlUnit cu = userDao.findOrganization(cuId);
		state.setCuName(cu.getCuName());
		List<LabourOnduty> list;
		int beginTime=getBeginTime();
		int endTime=beginTime+1;
		list = labourOndutyDao.getCurrentTimeLabourOnduty(sFormat.format(date),
				(beginTime<10?"0":"")+beginTime+":00:00",(endTime<10?"0":"")+endTime+":00:00",cu.getFullPath());
		for (LabourOnduty rec : list) {
			if (rec.getPersonType().equals("1") ) {
				state.setNumEmp(rec.getQuantityOnduty());
			} else if (rec.getPersonType().equals("2")
					|| rec.getPersonType().equals("3")
					|| rec.getPersonType().equals("4")) {
				state.setNumTemp((state.getNumTemp() == null ? 0 : state
						.getNumTemp()) + rec.getQuantityOnduty());
			} else if (rec.getPersonType().equals("5")) {
				state.setNumOther(rec.getQuantityOnduty());
			}
		}

		return state;
	}

	@Override
	public List<LabourOndutyState> getNumberHistory(String cuId,
			Date startDate, Date endDate, String interval) {
		ControlUnit cu = userDao.findOrganization(cuId);

		List<LabourOndutyDayState> list = labourOndutyDao
				.getHistoryLabourOnduty(sFormat.format(startDate),
						sFormat.format(endDate), cu.getFullPath());
		Map<String, LabourOndutyState> map = new HashMap<String, LabourOndutyState>();
		for (LabourOndutyDayState rec : list) {
			LabourOndutyState state = null;
			if (map.containsKey(sFormat.format(rec.getBizDate()))) {
				state = map.get(sFormat.format(rec.getBizDate()));
			} else {
				state = new LabourOndutyState();
				state.setCuId(cuId);
				state.setCuName(cu.getCuName());
				state.setDayTime(sFormat.format(rec.getBizDate()));
				map.put(sFormat.format(rec.getBizDate()), state);
			}

			if (rec.getPersonType().equals("1")) {
				state.setNumEmp(rec.getAvgQuantity());
			} else if (rec.getPersonType().equals("2")
					|| rec.getPersonType().equals("3")
					|| rec.getPersonType().equals("4")) {
				state.setNumTemp((state.getNumTemp() == null ? 0 : state
						.getNumTemp()) + rec.getAvgQuantity());
			} else if (rec.getPersonType().equals("5")) {
				state.setNumOther(rec.getAvgQuantity());
			}
		}
		return new ArrayList<LabourOndutyState>(map.values());
	}

	@Override
	public List<LabourEfficiency> getEfficiencyHistory(String cuId,
			Date startDate, Date endDate) {
		ControlUnit cu = userDao.findOrganization(cuId);
		return labourEfficiencyDao.getHistoryLabourEfficiency(
				sFormat.format(startDate), sFormat.format(endDate),
				cu.getFullPath());

	}

	@Override
	public LabourEfficiency getTimePeriodEfficience(String cuId, Date bizDate,
			Integer timePeriod) {
		ControlUnit cu = userDao.findOrganization(cuId);
		// 如果timePeriod为空，默认当前一小时
		if (timePeriod == null) {
			Calendar currentTime = Calendar.getInstance();
			currentTime.setTime(new Date());
			timePeriod = currentTime.get(Calendar.HOUR_OF_DAY) - 1;
			if (timePeriod < 0) {
				timePeriod = 24;
			}
		}
		List<LabourEfficiency> list = labourEfficiencyDao.getLabourEfficiency(
				sFormat.format(bizDate), timePeriod, cu.getFullPath());
		LabourEfficiency result=new LabourEfficiency();
		result.setCuId(cuId);
		result.setCuName(cu.getCuName());
		double efficiency=0.0;
		if (list != null && list.size()>0) {
			for(LabourEfficiency le:list){
				efficiency+=le.getPeriodEfficiency();
			}
			efficiency=efficiency/list.size();
		}
		result.setEfficiency(efficiency);
		return result;
	}

	private int getBeginTime(){
		Calendar currentTime = Calendar.getInstance();
		currentTime.setTime(new Date());
		int timePeriod = currentTime.get(Calendar.HOUR_OF_DAY) - 1;
		if (timePeriod < 0) {
			timePeriod = 24;
		}
		return timePeriod;
	}
}