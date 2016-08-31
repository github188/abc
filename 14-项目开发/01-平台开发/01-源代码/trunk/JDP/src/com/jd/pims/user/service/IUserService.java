package com.jd.pims.user.service;

public interface IUserService {
	/**
	 * 用户登录
	 * @param account 用户名称
	 * @param password 用户名称
	 * @return 返回json字符串，包括员工的基本信息
	 */
	String login(String account,String password);
	/**
	 * 用户登出
	 * @param empId 员工ID
	 * @return 返回json字符串，包括用户退出平台的信息提示
	 */
	String logout(String empId);
}
