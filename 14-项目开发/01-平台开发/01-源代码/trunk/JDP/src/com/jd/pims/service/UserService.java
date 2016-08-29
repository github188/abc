package com.jd.pims.service;

import java.util.List;

import com.jd.pims.model.PageBean;
import com.jd.pims.model.User;

public interface UserService {
	
	String addInfo(User addInfo);
	
	List<User> getAll();
	
	String delete(String id);
	
	User findById(String id);
	
	String update(User addInfo);

	PageBean getAllByPage(int pageNum,String userName);

	String updateIcon(User user);

}
