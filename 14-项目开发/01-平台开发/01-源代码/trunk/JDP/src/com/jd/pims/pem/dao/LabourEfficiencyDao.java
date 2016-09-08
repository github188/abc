package com.jd.pims.pem.dao;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

import com.jd.pims.comm.IBaseDao;
import com.jd.pims.pem.model.LabourEfficiency;

public interface LabourEfficiencyDao extends IBaseDao {

	/**
	 * 
	 * @param bizDate
	 * @param timePeriod
	 * @param fullPath
	 * @return
	 */
	LabourEfficiency getLabourEfficiency(@Param("bizDate")Date bizDate, @Param("timePeriod")Integer timePeriod,
			@Param("fullPath")String fullPath);

}
