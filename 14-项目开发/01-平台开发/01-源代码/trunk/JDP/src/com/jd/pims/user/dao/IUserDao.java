package com.jd.pims.user.dao;

import com.jd.pims.user.model.User;

public interface IUserDao {

	void create(User user);
	
	void update(User user);
	
	void delete(String userId);
	
	User findByAccount(String account);
}