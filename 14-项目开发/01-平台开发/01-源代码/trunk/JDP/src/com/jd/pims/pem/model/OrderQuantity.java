package com.jd.pims.pem.model;

import java.util.Date;

import javax.persistence.Entity;

import com.google.gson.Gson;
import com.jd.pims.comm.BaseDataModel;

@Entity
public class OrderQuantity extends BaseDataModel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2814189977670150288L;

	private String id;
	
	private String cuId;
	
	private Date bizDate;
	
	private Integer timePeriod;
	
	private Integer orderQuantity;
	
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

	public Integer getTimePeriod() {
		return timePeriod;
	}

	public void setTimePeriod(Integer timePeriod) {
		this.timePeriod = timePeriod;
	}


	public Integer getOrderQuantity() {
		return orderQuantity;
	}

	public void setOrderQuantity(Integer orderQuantity) {
		this.orderQuantity = orderQuantity;
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
	
}
