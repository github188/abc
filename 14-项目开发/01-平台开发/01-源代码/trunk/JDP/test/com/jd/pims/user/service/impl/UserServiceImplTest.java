package com.jd.pims.user.service.impl;

import static org.junit.Assert.*;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jd.pims.comm.PIMSException;
import com.jd.pims.user.model.ControlUnit;
import com.jd.pims.user.model.Employee;
import com.jd.pims.user.service.IUserService;

@ContextConfiguration(locations = { "classpath:spring.xml",
		"classpath:spring-mvc.xml", "classpath:spring-mybatis.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceImplTest extends
AbstractTransactionalJUnit4SpringContextTests{
	
	private static final Logger logger = Logger
			.getLogger(UserServiceImplTest.class.getName());
	
	@Autowired
	private IUserService userService;
	
	@Test
	public void testLogin() {
		try {
			Employee emp=userService.login("jd.cn", "123");
			Assert.assertTrue("成功返回："+emp, emp!=null);
		} catch (PIMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testLogout() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetOrganizations() {
		List<ControlUnit> list=userService.getOrganizations();
		if(list!=null){
			Assert.assertTrue("成功返回："+list.size(), true);
			for(ControlUnit cu:list){
				logger.info(cu);
			}
		}else{
			Assert.assertFalse("失败返回：记录为空！", true);
		}
	}

}
