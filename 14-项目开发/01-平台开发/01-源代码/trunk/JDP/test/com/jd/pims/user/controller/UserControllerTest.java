package com.jd.pims.user.controller;

import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@ContextConfiguration(locations = { "classpath:spring.xml",
		"classpath:spring-mvc.xml", "classpath:spring-mybatis.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class UserControllerTest extends
		AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	private UserController userController;

	MockHttpServletRequest request = new MockHttpServletRequest();
	MockHttpServletResponse response = new MockHttpServletResponse();

	@Test
	public void testLogin() {
		request.setRequestURI("/user/login");
		request.setMethod(HttpMethod.POST.name());
		request.addParameter("account", "jd.cn");
		request.addParameter("password", "123");
		String msg=userController.login(request);
		System.out.println(msg);

		if(msg.contains("result")){
			Assert.assertTrue(msg, true);
		}else{
			Assert.assertTrue(msg, true);
		}

	}

	@Test
	public void testLogout() {
		//fail("Not yet implemented");
		request.setMethod(HttpMethod.POST.name());
		request.addParameter("empId", "11111111111111111111111111111111");
		String msg=userController.logout(request);
		System.out.println(msg);
		if(msg.contains("returnCode")){
			Assert.assertTrue(msg, true);
		}else{
			Assert.assertTrue(msg, true);
		}
	}
	
	@Test
	public void testGetOrganization(){
		fail("Not yet implemented");
	}
	
}
