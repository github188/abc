package com.jd.pims.pem.service.impl;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jd.pims.pem.service.IChartService;

@ContextConfiguration(locations = { "classpath:spring.xml",
		"classpath:spring-mvc.xml", "classpath:spring-mybatis.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class ChartServiceImplTest extends
		AbstractTransactionalJUnit4SpringContextTests {
	private static final Logger logger = Logger
			.getLogger(ChartServiceImplTest.class.getName());
	
	@Autowired
	private IChartService service;

	@Test
	public void testGetNumberOnDuty() {
		logger.info("图表上的人效接口测试");
		try{
			List<Map<String, Object>> results=service.getNumberOnDuty("京东集团");
			Assert.assertTrue("返回记录数"+results.size(), results != null);
		}catch(Exception e){
			logger.debug("", e);
			fail("测试失败！原因："+e.getMessage());
		}
	}

	@Test
	public void testGetNumberHistory() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetEfficience() {
		logger.info("图表上的人效接口测试");
		try{
			service.getEfficience("京东集团");
		}catch(Exception e){
			logger.debug("", e);
			fail("测试失败！原因："+e.getMessage());
		}
	}

	@Test
	public void testGetEfficiencyHistory() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetData() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testGetMyCenterData(){
		logger.info("分拣中心在岗人数接口测试");
		try{
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.YEAR, 2016);
			calendar.set(Calendar.MONTH, 10);//11月
			calendar.set(Calendar.DAY_OF_MONTH, 18);
			service.getMyCenterData("北京马驹桥分拣中心",calendar.getTime());
		}catch(Exception e){
			logger.debug("", e);
			fail("测试失败！原因："+e.getMessage());
		}
	}

}
