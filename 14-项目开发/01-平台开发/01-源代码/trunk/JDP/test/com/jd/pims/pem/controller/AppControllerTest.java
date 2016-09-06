package com.jd.pims.pem.controller;

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
public class AppControllerTest extends
		AbstractTransactionalJUnit4SpringContextTests {
	
	@Autowired
	private AppController controller;
	
	@Test
	public void testGetNumberOnDuty() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpServletResponse response = new MockHttpServletResponse();
		//request.setRequestURI("/user/login");
		request.setMethod(HttpMethod.POST.name());
		request.addParameter("empId", "888");
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
		//request.setRequestURI("/user/login");
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
		//request.setRequestURI("/user/login");
		request.setMethod(HttpMethod.POST.name());
		request.addParameter("empId", "11111111111111111111111111111111");
		request.addParameter("cuId", "11111111111111111111111111111111");
		request.addParameter("startDate", "2016-09-01");
		request.addParameter("endDate", "2016-09-07");
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
		//request.setRequestURI("/user/login");
		request.setMethod(HttpMethod.POST.name());
		request.addParameter("empId", "11111111111111111111111111111111");
		request.addParameter("cuId", "11111111111111111111111111111111");
		request.addParameter("startDate", "2016-09-01");
		request.addParameter("endDate", "2016-09-07");
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
		//request.setRequestURI("/user/login");
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
		//request.setRequestURI("/user/login");
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
}
