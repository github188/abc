package com.jd.pims.user.dao;

import com.jd.pims.user.model.User;

public interface UserDao {
	
	User findByAccount(String account);
}