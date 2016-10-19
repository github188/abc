package com.jd.pims.pem.dao;

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
	LabourEfficiency getLabourEfficiency(@Param("bizDate")String bizDate, @Param("timePeriod")Integer timePeriod,
			@Param("fullPath")String fullPath,@Param("beginTime")String beginTime,@Param("endTime")String endTime);
	
	/**
	 * 取历史人效
	 * @param startDate 开始人效
	 * @param endDate
	 * @param fullPath
	 * @return
	 */
	List<LabourEfficiency> getHistoryLabourEfficiency(@Param("startDate")String startDate, @Param("endDate")String endDate,
			@Param("fullPath")String fullPath);

	List<Map<String, Object>> getEfficiencyOrderForChart(
			@Param("date")String time, @Param("timePeriod")int timePeriod,@Param("name")String name);
	
	List<Map<String, Object>> getTodayOrderForChart(
			@Param("date")String time,@Param("name")String name);
	
	List<Map<String, Object>> getHistoryEfficiencyForChart(
			@Param("startDate")String startDate, @Param("endDate")String endDate, @Param("timePeriod")int timePeriod, @Param("name")String name);
	
	List<Map<String, Object>> getHistoryEfficiencyOrderForChart(
			@Param("startDate")String startDate, @Param("endDate")String endDate, @Param("timePeriod")int timePeriod, @Param("name")String name);
	
}
