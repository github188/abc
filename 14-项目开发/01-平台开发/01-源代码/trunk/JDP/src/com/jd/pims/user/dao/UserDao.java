package com.jd.pims.user.dao;

import com.jd.pims.comm.IBaseDao;
import com.jd.pims.user.model.Employee;
import com.jd.pims.user.model.User;

public interface UserDao extends IBaseDao{
	
	User getUserByAccount(String account);
	
	Employee getEmployeeById(String id);
	
}