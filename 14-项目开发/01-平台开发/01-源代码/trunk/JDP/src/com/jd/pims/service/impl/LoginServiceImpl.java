package com.jd.pims.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jd.pims.dao.UserDao;
import com.jd.pims.service.AbsService;

@Service("loginServiceImpl")
public class LoginServiceImpl extends AbsService {
	@Autowired
	private UserDao userDaoImpl;

	@Override
	public String login(String usr, String pwd) {
		return null;
	}


	

}
