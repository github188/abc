/**
 * 
 */
package com.jd.pims.pem.service.impl;

import java.util.Date;
import java.util.List;

import com.jd.pims.pem.model.LabourEfficiency;
import com.jd.pims.pem.model.LabourOndutyDayState;
import com.jd.pims.pem.model.LabourOndutyState;
import com.jd.pims.pem.service.IChartService;

/**
 * @author Administrator
 *
 */
public class ChartServiceImpl implements IChartService {

	@Override
	public List<LabourOndutyState> getNumberOnDuty(String cuId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LabourOndutyState> getNumberOnDuty(String cuId, Date date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LabourOndutyDayState> getNumberHistory(String cuId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LabourEfficiency getTimePeriodEfficience(String cuId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LabourEfficiency> getEfficiencyHistory(String cuId) {
		// TODO Auto-generated method stub
		return null;
	}

}
