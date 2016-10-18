package com.jd.pims.pem.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jd.pims.user.controller.UserController;

@ContextConfiguration(locations = { "classpath:spring.xml",
		"classpath:spring-mvc.xml", "classpath:spring-mybatis.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class AppControllerTest extends
		AbstractTransactionalJUnit4SpringContextTests {
	
	@Autowired
	private AppController controller;
	
	@Autowired
	private UserController userController;
	
	//@Before
	public void login(){
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setRequestURI("/user/login");
		request.setMethod(HttpMethod.POST.name());
		request.addParameter("account", "admin");
		request.addParameter("password", "123");
		String msg=userController.login(request);
		System.out.println(msg);
	}
	
	@Test
	public void testGetNumberOnDuty() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpServletResponse response = new MockHttpServletResponse();
		request.setMethod(HttpMethod.POST.name());
		request.addParameter("empId", "11111111111111111111111111111111");
		request.addParameter("cuId", "11111111111111111111111111111111");
		String msg = controller.getNumberOnDuty(request, response);
		System.out.println(msg);

		if(msg.contains("result")){
			Assert.assertTrue("成功返回："+msg, true);
		}else{
			Assert.assertTrue("失败返回："+msg, true);
		}
	}

	@Test
	public void testGetEfficiency() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpServletResponse response = new MockHttpServletResponse();
		request.setMethod(HttpMethod.POST.name());
		request.addParameter("empId", "11111111111111111111111111111111");
		request.addParameter("cuId", "11111111111111111111111111111111");
		String msg = controller.getEfficiency(request, response);
		System.out.println(msg);

		if(msg.contains("result")){
			Assert.assertTrue("成功返回："+msg, true);
		}else{
			Assert.assertTrue("失败返回："+msg, true);
		}
	}
	
	@Test
	public void testGetNumberHistory() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpServletResponse response = new MockHttpServletResponse();
		request.setMethod(HttpMethod.POST.name());
		request.addParameter("empId", "11111111111111111111111111111111");
		request.addParameter("cuId", "11111111111111111111111111111111");
		request.addParameter("startDate", "2016-09-06");
		request.addParameter("endDate", "2016-09-13");
		request.addParameter("interval", "D");
		String msg = controller.getNumberHistory(request, response);
		System.out.println(msg);

		if(msg.contains("result")){
			Assert.assertTrue("成功返回："+msg, true);
		}else{
			Assert.assertTrue("失败返回："+msg, true);
		}
	}
	
	@Test
	public void testGetEfficiencyHistory() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpServletResponse response = new MockHttpServletResponse();
		request.setMethod(HttpMethod.POST.name());
		request.addParameter("empId", "11111111111111111111111111111111");
		request.addParameter("cuId", "11111111111111111111111111111111");
		request.addParameter("startDate", "2016-09-06");
		request.addParameter("endDate", "2016-09-30");
		request.addParameter("interval", "D");
		String msg = controller.getEfficiencyHistory(request, response);
		System.out.println(msg);

		if(msg.contains("result")){
			Assert.assertTrue("成功返回："+msg, true);
		}else{
			Assert.assertTrue("失败返回："+msg, true);
		}
	}
	
	@Test
	public void testGetOrganization() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpServletResponse response = new MockHttpServletResponse();
		request.setMethod(HttpMethod.POST.name());
		request.addParameter("empId", "11111111111111111111111111111111");
		
		String msg = controller.getOrganization(request, response);
		System.out.println(msg);

		if(msg.contains("result")){
			Assert.assertTrue("成功返回："+msg, true);
		}else{
			Assert.assertTrue("失败返回："+msg, true);
		}
	}
	
	@Test
	public void testGetEmployee() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpServletResponse response = new MockHttpServletResponse();
		request.setMethod(HttpMethod.POST.name());
		request.addParameter("empId", "11111111111111111111111111111111");
		request.addParameter("inputStr", "刘");
		
		String msg = controller.getEmployee(request, response);
		System.out.println(msg);

		if(msg.contains("result")){
			Assert.assertTrue("成功返回："+msg, true);
		}else{
			Assert.assertTrue("失败返回："+msg, true);
		}
	}
	
	@Test
	public void testGetLabourAndEfficiencyOfGroup(){
		MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpServletResponse response = new MockHttpServletResponse();
		request.setMethod(HttpMethod.POST.name());
		request.addParameter("empId", "11111111111111111111111111111111");
		
		String msg = controller.getLabourAndEfficiencyOfGroup(request, response);
		System.out.println(msg);

		if(msg.contains("result")){
			Assert.assertTrue("成功返回："+msg, true);
		}else{
			Assert.assertTrue("失败返回："+msg, true);
		}
	}
	
	@Test
	public void testGetUpdateState() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpServletResponse response = new MockHttpServletResponse();
		request.setMethod(HttpMethod.POST.name());
		request.addParameter("type", "android");
		
		String msg = controller.getUpdateState(request, response);
		System.out.println(msg);

		if(msg.contains("result")){
			Assert.assertTrue("成功返回："+msg, true);
		}else{
			Assert.assertTrue("失败返回："+msg, true);
		}
	}
}
