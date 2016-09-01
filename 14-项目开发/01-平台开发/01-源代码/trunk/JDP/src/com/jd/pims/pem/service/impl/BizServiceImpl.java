package com.jd.pims.pem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jd.pims.pem.service.AbsService;
import com.jd.pims.user.dao.UserDao;

@Service("bizServiceImpl")
public class BizServiceImpl extends AbsService {
	@Autowired
	private UserDao userDaoImpl;

	

}
