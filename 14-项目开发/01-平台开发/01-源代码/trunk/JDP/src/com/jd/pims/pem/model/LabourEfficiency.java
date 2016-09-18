package com.jd.pims.pem.model;

import java.util.Date;

import javax.persistence.Entity;

import com.jd.pims.comm.BaseDataModel;

@Entity
public class LabourEfficiency extends BaseDataModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6644930771146714269L;
	private String id;
	private String cuId;
	private String cuName;
	private Date bizDate;
	private Integer timePeriod;
	private Integer numberOnduty=0;
	private Integer orderQuantity=0;
	private Double efficiency=0.0;
	private Double periodEfficiency=0.0;
	private Double avgEfficiency=0.0;
	private Date createTime;
	private Date lastUpdateTime;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCuId() {
		return cuId;
	}
	public void setCuId(String cuId) {
		this.cuId = cuId;
	}
	public String getCuName() {
		return cuName;
	}
	public void setCuName(String cuName) {
		this.cuName = cuName;
	}
	
	
	public Date getBizDate() {
		return bizDate;
	}
	public void setBizDate(Date bizDate) {
		this.bizDate = bizDate;
	}
	public Double getPeriodEfficiency() {
		return periodEfficiency;
	}
	public void setPeriodEfficiency(Double periodEfficiency) {
		this.periodEfficiency = periodEfficiency;
	}
	public Double getAvgEfficiency() {
		return avgEfficiency;
	}
	public void setAvgEfficiency(Double avgEfficiency) {
		this.avgEfficiency = avgEfficiency;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	public Integer getTimePeriod() {
		return timePeriod;
	}
	public void setTimePeriod(Integer timePeriod) {
		this.timePeriod = timePeriod;
	}
	public Double getEfficiency() {
		return efficiency;
	}
	public void setEfficiency(Double efficiency) {
		this.efficiency = efficiency;
	}
	public Integer getNumberOnduty() {
		return numberOnduty;
	}
	public void setNumberOnduty(Integer numberOnduty) {
		this.numberOnduty = numberOnduty;
	}
	public Integer getOrderQuantity() {
		return orderQuantity;
	}
	public void setOrderQuantity(Integer orderQuantity) {
		this.orderQuantity = orderQuantity;
	}
	

}
