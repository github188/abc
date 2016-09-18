package com.jd.pims.user.model;


import javax.persistence.Entity;

import com.google.gson.Gson;
import com.jd.pims.comm.BaseDataModel;

@Entity
public class Employee extends BaseDataModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7423717110374658057L;

	private String id;
	
	private String empCode;
	
	private String empName;
	
	private String sex;
	
	private Integer age;
	
	private String telephone;//手机
	
	private String officeTel;//办公电话
	
	private String address;
	
	private String nativePlace;//户籍
	
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



	public String getPersonCode() {
		return empCode;
	}



	public void setPersonCode(String personCode) {
		this.empCode = personCode;
	}



	public String getEmpName() {
		return empName;
	}



	public void setEmpame(String empName) {
		this.empName = empName;
	}



	public String getSex() {
		return sex;
	}



	public void setSex(String sex) {
		this.sex = sex;
	}



	public Integer getAge() {
		return age;
	}



	public void setAge(Integer age) {
		this.age = age;
	}



	public String getTelephone() {
		return telephone;
	}



	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}



	public String getOfficeTel() {
		return officeTel;
	}



	public void setOfficeTel(String officeTel) {
		this.officeTel = officeTel;
	}



	public String getAddress() {
		return address;
	}



	public void setAddress(String address) {
		this.address = address;
	}



	public String getNativePlace() {
		return nativePlace;
	}



	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
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
	
	

}
