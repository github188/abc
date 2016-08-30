package com.jd.pims.pem.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jd.pims.pem.dao.UserDao;
import com.jd.pims.pem.service.AbsService;

@Service("bizServiceImpl")
public class BizServiceImpl extends AbsService {
	@Autowired
	private UserDao userDaoImpl;

	public String pim(HttpServletRequest request) {
		userDaoImpl.login();
		return "BizServiceImpl";
	}


	

}
