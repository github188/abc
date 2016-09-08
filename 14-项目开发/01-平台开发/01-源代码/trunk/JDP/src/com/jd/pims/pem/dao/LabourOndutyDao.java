package com.jd.pims.pem.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jd.pims.comm.IBaseDao;
import com.jd.pims.pem.model.LabourOnduty;
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

	/**
	 * 返回历史在岗人数
	 * 
	 * @param currentDate
	 * @param cuId
	 * @return
	 */
	List<LabourOndutyState> getHistoryLabourOnduty(
			@Param("startDate") Date startDate,
			@Param("endDate") String endDate,
			@Param("fullPath") String fullPath);

}