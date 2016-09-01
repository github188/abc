package com.jd.pims.user.dao.impl;

import org.springframework.stereotype.Service;

import com.jd.pims.user.dao.IUserDao;
import com.jd.pims.user.model.User;

@Service("userDaoImpl")
public class UserDaoImpl implements IUserDao{
	
	@Override
	public User findByAccount(String account) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
