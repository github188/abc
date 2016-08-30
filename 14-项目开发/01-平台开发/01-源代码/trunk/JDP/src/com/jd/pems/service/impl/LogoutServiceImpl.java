package com.jd.pems.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jd.pems.dao.UserDao;
import com.jd.pems.service.AbsService;

@Service("logoutServiceImpl")
public class LogoutServiceImpl extends AbsService {
	@Autowired
	private UserDao userDaoImpl;

	@Override
	public String logout(String usr, String pwd) {
		return null;
	}


	

}
