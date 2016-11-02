package com.jd.pims.user.service;

import java.io.InputStream;
import java.util.List;

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
	public List<ControlUnit> getOrganizations();
	
	/**
	 * 获取子控制单元（组织架构）列表
	 * @return
	 */
	public List<ControlUnit> getSubOrganizations(String parentId);
	
	/**
	 * 获取控制单元
	 * @param cuId
	 * @return
	 */
	public ControlUnit findOrganization(String cuId);
	
	/**
	 * 取根节点的组织架构
	 * @return
	 */
	public ControlUnit findRootOrganization();
	
	/**
	 * 根据人员相关查询查询相似人员列表 
	 * @param inputStr
	 * @return
	 */
	public List<Employee> searchEmployee(String inputStr);
	
	public String createAccount(InputStream inputStream);
}
