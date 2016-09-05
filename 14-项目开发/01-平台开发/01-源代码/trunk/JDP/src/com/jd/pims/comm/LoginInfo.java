package com.jd.pims.comm;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.jd.pims.user.model.Employee;
import com.jd.pims.user.model.User;
/**
 * 用户登录信息缓存
 *
 */
public class LoginInfo {
	private static final Logger logger = Logger
			.getLogger(LoginInfo.class.getName());
	private static Map<String,User> users=new HashMap<String,User>();
	private static Map<String,Employee> emplopyees=new HashMap<String,Employee>();
	
	/**
	 * 用户登录注册信息
	 * @param user
	 * @param employee
	 */
	public static synchronized void add(User user,Employee employee){
		users.put(user.getUserName(), user);
		emplopyees.put(employee.getId(), employee);
		logger.info("New user login, the total number about loginning user is: "+users.size());
	}
	
	/**
	 * 用户登出
	 * @param empId
	 */
	public static synchronized void remove(String empId){
		for(User user:users.values()){
			if(user.getPersonId().equals(empId)){
				users.remove(user.getUserName());
				break;
			}
		}
		emplopyees.remove(empId);
		logger.info("User logout, the total number about loginning user is: "+users.size());
	}
	
	public static boolean isLogin(String empId){
		if(emplopyees.containsKey(empId)){
			return true;
		}
		return false;
	}
		
}
