package com.jd.pims.pem.model;

import java.sql.Time;
import java.util.Date;

import javax.persistence.Entity;

import com.jd.pims.comm.BaseDataModel;

@Entity
public class LabourOnduty extends BaseDataModel{
	
	/*
	 * 1 正式工
	 * 2 长期派遣工
	 * 3 外包工
	 * 4 小时工
	 * 5 其它
	 */

	public static String TYPE_EMP="EMP";
	
	public static String TYPE_TEMP="TEMP";
	
	public static String TYPE_OTEH="OTEH";
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
	
	private Integer hour;

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



	public Integer getHour() {
		return hour;
	}



	public void setHour(Integer hour) {
		this.hour = hour;
	}
	

}
