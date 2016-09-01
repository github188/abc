package com.jd.pims.user.model;

import java.io.Serializable;

import javax.persistence.Entity;

/**
 * 用户领域对象
 */
@Entity
public class User implements Serializable{

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

	public String toString() {
		return "id:" + id + ",userName:" + userName + ",personId:" + personId
				+ ",userType:" + userType + ",status:" + status
				+ ",controlunitid:" + controlunitid;
	}

}