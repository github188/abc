package com.jd.pims.user.service.impl;


import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jd.pims.comm.LoginInfo;
import com.jd.pims.comm.PIMSException;
import com.jd.pims.user.dao.UserDao;
import com.jd.pims.user.model.ControlUnit;
import com.jd.pims.user.model.Employee;
import com.jd.pims.user.model.User;
import com.jd.pims.user.service.IUserService;

@Service("userService")
public class UserServiceImpl implements IUserService{
	
	@Autowired
	private UserDao userDao;
	

	@Override
	public Employee login(String account, String password) throws PIMSException{
		User user=userDao.getUserByAccount(account);
		if(user==null){
			throw new PIMSException(1,"系统不存在用户："+account);
		}
		if(!user.getPassword().equals(password)){
			throw new PIMSException(2,"用户密码不正确");
		}
		Employee emp = userDao.getEmployeeById(user.getPersonId());
		LoginInfo.add(user, emp);
		return emp;
	}

	@Override
	public void logout(String empId) {
		LoginInfo.remove(empId);
	}

	@Override
	public LinkedList<ControlUnit> getOrganization() {
		// TODO Auto-generated method stub
		return null;
	}

}
