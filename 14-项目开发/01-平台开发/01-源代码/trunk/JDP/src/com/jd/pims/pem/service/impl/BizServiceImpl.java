package com.jd.pims.pem.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

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
	public List<LabourOndutyDayState> getNumberHistory(String cuId,
			Date startDate, Date endDate, String interval) {
		// TODO Auto-generated method stub
		ControlUnit cu = userDao.findOrganization(cuId);
		return labourOndutyDao.getHistoryLabourOnduty(startDate, endDate,
				cu.getFullPath());
	}

	@Override
	public LinkedList<LabourEfficiency> getEfficiencyHistory(String cuId,
			Date startDate, Date endDate, String interval) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LabourEfficiency getTimePeriodEfficience(String cuId, Date bizDate,
			Integer timePeriod) {
		// TODO Auto-generated method stub
		ControlUnit cu = userDao.findOrganization(cuId);
		try {
			return labourEfficiencyDao.getLabourEfficiency(
					sFormat.parse(sFormat.format(bizDate)), timePeriod,
					cu.getFullPath());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			logger.debug("日期解释错误：", e);
			return null;
		}
	}

}