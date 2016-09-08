package com.jd.pims.user.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jd.pims.comm.LoginInfoCache;
import com.jd.pims.comm.PIMSException;
import com.jd.pims.user.dao.UserDao;
import com.jd.pims.user.model.ControlUnit;
import com.jd.pims.user.model.Employee;
import com.jd.pims.user.model.User;
import com.jd.pims.user.service.IUserService;

@Service("userService")
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserDao userDao;

	private static final Logger logger = Logger.getLogger(UserServiceImpl.class
			.getName());

	@Override
	public Employee login(String account, String password) throws PIMSException {
		User user = userDao.getUserByAccount(account);
		if (user == null) {
			throw new PIMSException(1, "系统不存在用户：" + account);
		}
		if (!user.getPassword().equals(password)) {
			throw new PIMSException(2, "用户密码不正确");
		}
		Employee emp = userDao.getEmployeeById(user.getPersonId());
		LoginInfoCache.add(user, emp);
		return emp;
	}

	@Override
	public void logout(String empId) {

		Employee emp = LoginInfoCache.getLoginEmployee(empId);
		if (emp != null) {
			logger.debug("员工" + emp.getPersonName() + "退出APP登录！");
		}
		LoginInfoCache.remove(empId);
	}

	@Override
	public List<ControlUnit> getOrganizations() {

		return userDao.getOrganizations();

	}

	@Override
	public List<ControlUnit> getSubOrganizations(String parentId) {
		return userDao.getSubOrganizations(parentId);
	}

	@Override
	public ControlUnit findOrganization(String cuId) {
		return userDao.findOrganization(cuId);
	}
}
