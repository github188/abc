package com.jd.pims.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jd.pims.dao.UserDao;
import com.jd.pims.service.AbsService;

@Service("bizServiceImpl")
public class BizServiceImpl extends AbsService {
	@Autowired
	private UserDao userDaoImpl;

	public String pim(HttpServletRequest request) {
		userDaoImpl.login();
		return "BizServiceImpl";
	}


	

}
