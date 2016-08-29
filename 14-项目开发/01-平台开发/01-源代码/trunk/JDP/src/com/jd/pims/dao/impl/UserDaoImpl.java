package com.jd.pims.dao.impl;

import org.springframework.stereotype.Service;

import com.jd.pims.dao.UserDao;

@Service("userDaoImpl")
public class UserDaoImpl implements UserDao{

	@Override
	public String login() {
		// TODO Auto-generated method stub
		return "123";
	}

	@Override
	public String logout() {
		// TODO Auto-generated method stub
		return null;
	}

}
