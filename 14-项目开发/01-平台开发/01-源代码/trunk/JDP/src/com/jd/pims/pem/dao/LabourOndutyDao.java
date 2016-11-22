package com.jd.pims.pem.dao;

import java.util.List;
import java.util.Map;

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
			@Param("currentDate") String currentDate,
			@Param("beginTime") String beginTime,
			@Param("endTime") String endTime,
			@Param("fullPath") String fullPath);
	
	List<LabourOnduty> getCurrentTimeLabourOnduty2(
			@Param("currentDate") String currentDate,
			@Param("beginTime") String beginTime,
			@Param("endTime") String endTime,
			@Param("fullPath") String fullPath);

	List<Map<String, Object>> getCurrentTimeLabourOndutyForChart(
			@Param("begin") String begin, @Param("end") String end,
			@Param("name") String name);
	
	List<Map<String, Object>> getCurrentTimeLabourOndutyForChart1(
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
	List<LabourOndutyState> getHistoryLabourOnduty(
			@Param("startDate") String startDate,
			@Param("endDate") String endDate, @Param("fullPath") String fullPath);
	
	/**
	 *  取分拣中心当天分时段在刚人数
	 *  逻辑：取过去每一小时最高人数，当前一小时取最新一条
	 * @param bizDate 业务日期，默认是当天
	 * @param currentTime 当前小时数,从1开始计算
	 * @param name 分拣中心名称
	 * @return
	 */
	List<LabourOnduty> getTodayLabourOndutyForEDC(
			@Param("bizDate") String bizDate,@Param("currentHour") int currentHour, @Param("cuName") String cuName);
	
	/**
	 *  取分拣中心当前一小时的最新人数
	 * @param bizDate 业务日期，默认是当天
	 * @param currentTime 当前小时数,从1开始计算
	 * @param name 分拣中心名称
	 * @return
	 */
	List<LabourOnduty> getCurrentTimeLabourOndutyForEDC(
			@Param("bizDate") String bizDate,@Param("currentHour") int currentHour, @Param("cuName") String cuName);
}