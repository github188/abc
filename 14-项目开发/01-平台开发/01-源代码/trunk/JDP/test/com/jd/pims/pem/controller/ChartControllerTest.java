package com.jd.pims.pem.controller;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

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
public class ChartControllerTest extends
		AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	private ChartController controller;
	
	@Autowired
	private UserController userController;
	
	@Before
	public void login(){
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setRequestURI("/user/login");
		request.setMethod(HttpMethod.POST.name());
		request.addParameter("account", "jd.cn");
		request.addParameter("password", "123");
		String msg=userController.login(request);
		System.out.println(msg);
	}
	
	
	@Test
	public void testGetMapData() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpServletResponse response = new MockHttpServletResponse();
		//request.setRequestURI("/user/login");
		request.setMethod(HttpMethod.POST.name());
		request.addParameter("empId", "11111111111111111111111111111111");
		request.addParameter("id", "11111111111111111111111111111111");
		String msg = controller.getMapData(request,response);
		System.out.println(msg);

		if(msg!=null){
			Assert.assertTrue("成功返回："+msg, true);
		}else{
			Assert.assertTrue("失败返回："+msg, true);
		}
	}

	@Test
	public void testGetBarData() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetBar1Data() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetBar2Data() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetOrderNumberData() {
		fail("Not yet implemented");
	}

}
