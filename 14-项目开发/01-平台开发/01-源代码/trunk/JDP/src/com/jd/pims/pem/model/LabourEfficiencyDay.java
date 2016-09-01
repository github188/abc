package com.jd.pims.pem.model;

import java.io.Serializable;
import java.util.Date;

import com.google.gson.Gson;

public class LabourEfficiencyDay implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6644930771146714269L;
	private String id;
	private String cuId;
	private Date bizDate;
	private Double labourEfficiency;
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

	public Date getBizDate() {
		return bizDate;
	}

	public void setBizDate(Date bizDate) {
		this.bizDate = bizDate;
	}

	public Double getLabourEfficiency() {
		return labourEfficiency;
	}

	public void setLabourEfficiency(Double labourEfficiency) {
		this.labourEfficiency = labourEfficiency;
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
	
	/**
	 * 转成json字符串
	 */
	public String toString(){
		return new Gson().toJson(this);
	}
}
