/**
 * 
 */
package com.jd.pims.pem.service;

import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 *
 */
public interface IChartService {
	/**
	 * 取实时在岗人数
	 * 
	 * @param cuId
	 *            控制单元（组织架构)id
	 * @return
	 */
	public List<Map<String, Object>> getNumberOnDuty(String cuId);

	/**
	 * 取历史在岗人数(当天的平均值)
	 * 
	 * @param cuId
	 *            控制单元（组织架构)id
	 * @param startDate
	 *            统计开始日期，日期类型
	 * @param endDate
	 *            统计结束日期，日期类型
	 * @return
	 */
	public List<Map<String, Object>> getNumberHistory(String cuId);

	/**
	 * 取按小时为单位人效统计
	 * 
	 * @param areaName  防区名称
	 * @param bizDate 业务日期
	 * @param timePeriod 时间点
	 * @return
	 */
	public List<Map<String, Object>> getEfficience(String areaName);

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
	public List<Map<String, Object>> getEfficiencyHistory(String cuId);
	
}
