package com.jd.pims.pem.service.impl;

import java.text.ParseException;
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
		try {
			list = labourOndutyDao.getCurrentTimeLabourOnduty(
					sFormat.parse(sFormat.format(date)), cu.getFullPath());
			for (LabourOnduty rec : list) {
				if (rec.getPersonType().equals("1")
						&& state.getNumEmp() == null) {
					state.setNumEmp(rec.getQuantityOnduty());
				} else if (rec.getPersonType().equals("2")
						|| rec.getPersonType().equals("3")) {
					state.setNumTemp((state.getNumTemp() == null ? 0 : state
							.getNumTemp()) + rec.getQuantityOnduty());
				} else if (rec.getPersonType().equals("5")) {
					state.setNumOther(rec.getQuantityOnduty());
				}
			}
		} catch (ParseException e) {
			logger.debug("日期解释错误：", e);
		}

		return state;
	}

	@Override
	public List<LabourOndutyState> getNumberHistory(String cuId,
			Date startDate, Date endDate, String interval) {
		ControlUnit cu = userDao.findOrganization(cuId);
		
		
		List<LabourOndutyDayState> list= labourOndutyDao.getHistoryLabourOnduty(startDate, endDate,
				cu.getFullPath());
		Map<String,LabourOndutyState> map=new HashMap<String,LabourOndutyState>();
		for (LabourOndutyDayState rec : list) {
			LabourOndutyState state =  null;
			if(map.containsKey(sFormat.format(rec.getBizDate()))){
				state=map.get(sFormat.format(rec.getBizDate()));
			}else{
				state=new LabourOndutyState();
				state.setCuId(cuId);
				state.setCuName(cu.getCuName());
				state.setDayTime(sFormat.format(rec.getBizDate()));
				map.put(sFormat.format(rec.getBizDate()), state);
			}
			
			if (rec.getPersonType().equals("1")
					&& state.getNumEmp() == null) {
				state.setNumEmp(rec.getAvgQuantity());
			} else if (rec.getPersonType().equals("2")
					|| rec.getPersonType().equals("3")) {
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
		try {
			return labourEfficiencyDao.getHistoryLabourEfficiency(
					sFormat.parse(sFormat.format(startDate)),
					sFormat.parse(sFormat.format(endDate)), cu.getFullPath());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			logger.debug("日期解释错误：", e);
			return null;
		}
	}

	@Override
	public LabourEfficiency getTimePeriodEfficience(String cuId, Date bizDate,
			Integer timePeriod) {
		ControlUnit cu = userDao.findOrganization(cuId);
		try {
			//如果timePeriod为空，默认当前一小时
			if(timePeriod==null){
				Calendar currentTime = Calendar.getInstance();
				currentTime.setTime(new Date());
				timePeriod=currentTime.get(Calendar.HOUR_OF_DAY)-1;
				if(timePeriod<0){
					timePeriod=24;
				}
			}
			return labourEfficiencyDao.getLabourEfficiency(
					sFormat.parse(sFormat.format(bizDate)), timePeriod,
					cu.getFullPath());
		} catch (ParseException e) {
			logger.debug("日期解释错误：", e);
			return null;
		}
	}

	
}