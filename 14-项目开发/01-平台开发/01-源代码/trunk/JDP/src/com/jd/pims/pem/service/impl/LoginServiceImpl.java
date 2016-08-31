package com.jd.pims.pem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jd.pims.pem.service.AbsService;
import com.jd.pims.user.dao.IUserDao;

@Service("loginServiceImpl")
public class LoginServiceImpl extends AbsService {
	@Autowired
	private IUserDao userDaoImpl;

	@Override
	public String login(String usr, String pwd) {
		return null;
	}


	

}
