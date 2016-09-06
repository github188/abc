package com.jd.pims.pem.service.impl;

import java.util.Date;
import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jd.pims.pem.dao.LabourEfficiencyDao;
import com.jd.pims.pem.dao.LabourOndutyDao;
import com.jd.pims.pem.dao.OrderQuantityDao;
import com.jd.pims.pem.model.LabourEfficiencyDay;
import com.jd.pims.pem.model.LabourEfficiencyHour;
import com.jd.pims.pem.model.LabourOndutyState;
import com.jd.pims.pem.service.IBizService;

@Service("bizServiceImpl")
public class BizServiceImpl implements IBizService {

	@Autowired
	private LabourEfficiencyDao labourEfficiencyDao;
	@Autowired
	private LabourOndutyDao labourOndutyDao;
	@Autowired
	private OrderQuantityDao orderQuantityDao;
	@Override
	public LinkedList<LabourOndutyState> getNumberOnDuty(String cuId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public LinkedList<LabourOndutyState> getNumberHistory(String cuId,
			Date startDate, Date endDate, String interval) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public LinkedList<LabourEfficiencyHour> getEfficiencyHour(String cuId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public LinkedList<LabourEfficiencyDay> getEfficiencyDay(String cuId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public LinkedList<LabourEfficiencyDay> getEfficiencyHistory(String cuId,
			Date startDate, Date endDate, String interval) {
		// TODO Auto-generated method stub
		return null;
	}
	

}