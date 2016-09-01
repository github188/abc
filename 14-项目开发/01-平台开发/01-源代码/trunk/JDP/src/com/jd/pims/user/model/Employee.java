package com.jd.pims.user.model;

import java.io.Serializable;

import javax.persistence.Entity;

import com.google.gson.Gson;

@Entity
public class Employee implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7423717110374658057L;

	private String id;
	
	private String personName;
	
	private String sex;
	
	private String orgId;

	private String orgCode;
	
	private String orgName;
	
	private String cuId;
	
	private String cuCode;
	
	private String cuName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getCuId() {
		return cuId;
	}

	public void setCuId(String cuId) {
		this.cuId = cuId;
	}

	public String getCuCode() {
		return cuCode;
	}

	public void setCuCode(String cuCode) {
		this.cuCode = cuCode;
	}

	public String getCuName() {
		return cuName;
	}

	public void setCuName(String cuName) {
		this.cuName = cuName;
	}
	
	/**
	 * 转成json字符串
	 */
	public String toString(){
		return new Gson().toJson(this);
	}
}
