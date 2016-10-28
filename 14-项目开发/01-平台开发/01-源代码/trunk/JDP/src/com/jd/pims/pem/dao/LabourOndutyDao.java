package com.jd.pims.pem.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.jd.pims.comm.IBaseDao;
import com.jd.pims.pem.model.LabourOnduty;
import com.jd.pims.pem.model.LabourOndutyDayState;

public interface LabourOndutyDao extends IBaseDao {

	/**
	 * 取当前时间点的在岗人数
	 * 
	 * @param cuId
	 * @return 以人员分类为分组，返回多条记录
	 */
	List<LabourOnduty> getCurrentTimeLabourOnduty(
			@Param("currentDate") String currentDate,
			@Param("beginTime") String beginTime,
			@Param("endTime") String endTime,
			@Param("fullPath") String fullPath);

	List<Map<String, Object>> getCurrentTimeLabourOndutyForChart(
			@Param("currentDate") String currentDate,
			@Param("begin") String begin, @Param("end") String end,
			@Param("name") String name);
	
	List<Map<String, Object>> getCurrentTimeLabourOndutyForChart1(
			@Param("currentDate") String currentDate,
			@Param("begin") String begin, @Param("end") String end,
			@Param("name") String name);
	
	List<Map<String, Object>> getHistoryLabourOndutyForChart(
			@Param("startDate") String startDate,
			@Param("endDate") String endDate, @Param("name") String name);

	/**
	 * 返回历史在岗人数
	 * 
	 * @param currentDate
	 * @param endDate
	 * @param cuId
	 * @return
	 */
	List<LabourOndutyDayState> getHistoryLabourOnduty(
			@Param("startDate") String startDate,
			@Param("endDate") String endDate, @Param("fullPath") String fullPath);
}