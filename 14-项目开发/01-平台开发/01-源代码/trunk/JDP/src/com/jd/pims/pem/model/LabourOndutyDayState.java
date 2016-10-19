package com.jd.pims.pem.model;

import java.util.Date;

import com.jd.pims.comm.BaseDataModel;

/**
 * 
 *
 */
public class LabourOndutyDayState extends BaseDataModel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6963268177463072134L;

	private String id;
	
	private String cuId;
	
	private Date bizDate;
	
	private  String personType;
	
	private Integer avgQuantity;
	
	private Integer maxQuantity;
	
	private Integer minQuantity;
	
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

	public String getPersonType() {
		return personType;
	}

	public void setPersonType(String personType) {
		this.personType = personType;
	}

	public Integer getAvgQuantity() {
		return avgQuantity;
	}

	public void setAvgQuantity(Integer avgQuantity) {
		this.avgQuantity = avgQuantity;
	}

	public Integer getMaxQuantity() {
		return maxQuantity;
	}

	public void setMaxQuantity(Integer maxQuantity) {
		this.maxQuantity = maxQuantity;
	}

	public Integer getMinQuantity() {
		return minQuantity;
	}

	public void setMinQuantity(Integer minQuantity) {
		this.minQuantity = minQuantity;
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
