package com.jd.pims.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.jd.pims.user.dao.UserDao;
import com.jd.pims.user.service.IUserService;

public class UserServiceImpl implements IUserService{
	
	@Autowired
	private UserDao userDao;

	@Override
	public String login(String account, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String logout(String empId) {
		// TODO Auto-generated method stub
		return null;
	}

}
