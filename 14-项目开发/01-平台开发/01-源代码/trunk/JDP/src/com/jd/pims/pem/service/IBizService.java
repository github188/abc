package com.jd.pims.pem.service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.jd.pims.pem.model.LabourEfficiencyDay;
import com.jd.pims.pem.model.LabourEfficiencyHour;
import com.jd.pims.pem.model.LabourOndutyDayState;
import com.jd.pims.pem.model.LabourOndutyState;
/**
 * 人效业务类 
 *
 */
public interface IBizService {
	/**
	 * 取实时在岗人数
	 * @param cuId 控制单元（组织架构)id
	 * @return
	 */
	public LabourOndutyState getNumberOnDuty(String cuId);
	
	/**
	 * 取指定日期的在岗人数
	 * @param cuId
	 * @param date
	 * @return
	 */
	public LabourOndutyState getNumberOnDuty(String cuId,Date date);
	
	/**
	 * 取历史在岗人数(当天的平均值)
	 * @param cuId 控制单元（组织架构)id
	 * @param startDate 统计开始日期，日期类型
	 * @param endDate 统计结束日期，日期类型
	 * @param interval 日期时间间隔，H：代表小时，D：代表天
	 * @return
	 */
	public List<LabourOndutyDayState> getNumberHistory(String cuId,Date startDate,Date endDate,String interval);
	
	/**
	 * 取按小时为单位人效统计
	 * @param cuId 控制单元
	 * @return
	 */
	public LinkedList<LabourEfficiencyHour> getEfficiencyHour(String cuId);
	
	/**
	 * 取按天为单位人效统计
	 * @param cuId 控制单元
	 * @return
	 */
	public LinkedList<LabourEfficiencyDay> getEfficiencyDay(String cuId);
	
	/**
	 * 取历史人效
	 * @param cuId 控制单元（组织架构)id
	 * @param startDate 统计开始日期，日期类型
	 * @param endDate 统计结束日期，日期类型
	 * @param interval 日期时间间隔，H：代表小时，D：代表天
	 * @return
	 */
	public LinkedList<LabourEfficiencyDay> getEfficiencyHistory(String cuId,Date startDate,Date endDate,String interval);
	
}