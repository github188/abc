package com.jd.pims.pem.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.jd.pims.comm.IBaseDao;
import com.jd.pims.pem.model.LabourOnduty;
import com.jd.pims.pem.model.LabourOndutyDayState;
import com.jd.pims.pem.model.LabourOndutyState;

public interface LabourOndutyDao extends IBaseDao {

	/**
	 * 取当前时间点的在岗人数
	 * 
	 * @param cuId
	 * @return 以人员分类为分组，返回多条记录
	 */
	List<LabourOnduty> getCurrentTimeLabourOnduty(
			@Param("currentDate") Date currentDate, @Param("fullPath")String fullPath);

	List<Map<String,Object>>getCurrentTimeLabourOndutyForChart(
			@Param("currentDate") Date currentDate,@Param("id")String id);
	List<Map<String,Object>> getHistoryLabourOndutyForChart(
			@Param("startDate") Date startDate,@Param("endDate") Date endDate,@Param("id")String id);
	/**
	 * 返回历史在岗人数
	 * 
	 * @param currentDate
	 * @param endDate
	 * @param cuId
	 * @return
	 */
	List<LabourOndutyDayState> getHistoryLabourOnduty(
			@Param("startDate") Date startDate,
			@Param("endDate") Date endDate,
			@Param("fullPath") String fullPath);

}