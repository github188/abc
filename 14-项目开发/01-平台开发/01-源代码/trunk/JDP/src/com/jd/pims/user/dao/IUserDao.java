package com.jd.pims.user.dao;

import com.jd.pims.user.model.User;

public interface IUserDao {
	
	User findByAccount(String account);
}