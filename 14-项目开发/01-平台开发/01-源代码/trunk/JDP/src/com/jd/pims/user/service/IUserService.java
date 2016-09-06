package com.jd.pims.user.service;

import java.util.LinkedList;

import com.jd.pims.comm.PIMSException;
import com.jd.pims.user.model.ControlUnit;
import com.jd.pims.user.model.Employee;


public interface IUserService {
	/**
	 * 用户登录
	 * @param account 用户名称
	 * @param password 用户名称
	 * @return 返回Employee对象
	 */
	Employee login(String account,String password) throws PIMSException;
	/**
	 * 用户登出
	 * @param empId 员工ID
	 * @return 返回json字符串，包括用户退出平台的信息提示
	 */
	void logout(String empId);
	
	/**
	 * 获取控制单元（组织架构）列表
	 * @return
	 */
	public LinkedList<ControlUnit> getOrganization();
}
