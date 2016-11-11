package com.jd.pims.pem.service.impl;

import static org.junit.Assert.*;

import org.apache.log4j.Logger;
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
		fail("Not yet implemented");
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

}
