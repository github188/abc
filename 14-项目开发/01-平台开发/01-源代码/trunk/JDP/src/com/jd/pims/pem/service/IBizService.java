package com.jd.pims.pem.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.jd.pims.pem.model.LabourEfficiency;
import com.jd.pims.pem.model.LabourOndutyDayState;
import com.jd.pims.pem.model.LabourOndutyState;

/**
 * 人效业务类
 *
 */
public interface IBizService {
	/**
	 * 取实时在岗人数
	 * 
	 * @param cuId
	 *            控制单元（组织架构)id
	 * @return
	 */
	public LabourOndutyState getNumberOnDuty(String cuId);

	/**
	 * 取指定日期的在岗人数
	 * 
	 * @param cuId
	 * @param date
	 * @return
	 */
	public LabourOndutyState getNumberOnDuty(String cuId, Date date);

	/**
	 * 取历史在岗人数(当天的平均值)
	 * 
	 * @param cuId
	 *            控制单元（组织架构)id
	 * @param startDate
	 *            统计开始日期，日期类型
	 * @param endDate
	 *            统计结束日期，日期类型
	 * @param interval
	 *            日期时间间隔，H：代表小时，D：代表天
	 * @return
	 */
	public List<LabourOndutyState> getNumberHistory(String cuId,
			Date startDate, Date endDate, String interval);

	/**
	 * 取按小时为单位人效统计
	 * 
	 * @param cuId  控制单元
	 * @param bizDate 业务日期
	 * @param timePeriod 时间点
	 * @return
	 */
	public LabourEfficiency getTimePeriodEfficience(
			String cuId, Date bizDate, Integer timePeriod);

	/**
	 * 取历史人效
	 * 
	 * @param cuId
	 *            控制单元（组织架构)id
	 * @param startDate
	 *            统计开始日期，日期类型
	 * @param endDate
	 *            统计结束日期，日期类型
	 * @param interval
	 *            日期时间间隔，H：代表小时，D：代表天
	 * @return
	 */
	public List<LabourEfficiency> getEfficiencyHistory(String cuId,
			Date startDate, Date endDate);
	
	
	public List<Map<String, Object>> queryYydata(String[] inputs,int pages);


}