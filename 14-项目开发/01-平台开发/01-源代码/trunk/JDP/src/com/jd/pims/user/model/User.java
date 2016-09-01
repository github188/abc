package com.jd.pims.user.model;

import java.io.Serializable;

import javax.persistence.Entity;

import com.google.gson.Gson;
import com.jd.pims.comm.BaseDataModel;

/**
 * 用户领域对象
 */
@Entity
public class User extends BaseDataModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -509152048516306632L;
	
	private String id;
	private String userName;
	private String password;
	private String personId; // 员工编号
	private String userType;
	private String status;
	private String controlunitid;

	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getControlunitid() {
		return controlunitid;
	}

	public void setControlunitid(String controlunitid) {
		this.controlunitid = controlunitid;
	}

	/**
	 * 转成json字符串数组
	 */
	public String toString() {
		return new Gson().toJson(this);
	}
}