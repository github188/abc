package com.jd.pims.pem.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

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
	
	/**
	 * 取历史人效
	 * @param startDate 开始人效
	 * @param endDate
	 * @param fullPath
	 * @return
	 */
	List<LabourEfficiency> getHistoryLabourEfficiency(@Param("startDate")Date startDate, @Param("endDate")Date endDate,
			@Param("fullPath")String fullPath);

	List<Map<String, Object>> getEfficiencyForChart(
			@Param("date")Date date, @Param("timePeriod")int timePeriod, @Param("id")String id);
	
	List<Map<String, Object>> getHistoryEfficiencyForChart(
			@Param("startDate")Date startDate, @Param("endDate")Date endDate, @Param("id")String id);

}
