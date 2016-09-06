package com.jd.pims.pem.model;

import java.sql.Time;
import java.util.Date;

import javax.persistence.Entity;

import com.google.gson.Gson;
import com.jd.pims.comm.BaseDataModel;

@Entity
public class LabourOnduty extends BaseDataModel{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6751032607343932028L;
	
	private String id;
	
	private String cuId;
	
	private Date bizDate;
	
	private Time timing;
	
	private String personType;
	
	private Integer quantityOnduty;
	
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



	public Time getTiming() {
		return timing;
	}



	public void setTiming(Time timing) {
		this.timing = timing;
	}



	public String getPersonType() {
		return personType;
	}



	public void setPersonType(String personType) {
		this.personType = personType;
	}



	public Integer getQuantityOnduty() {
		return quantityOnduty;
	}



	public void setQuantityOnduty(Integer quantityOnduty) {
		this.quantityOnduty = quantityOnduty;
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
