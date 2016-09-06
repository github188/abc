package com.jd.pims.pem.service.impl;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
@ContextConfiguration(locations = { "classpath:spring.xml",
		"classpath:spring-mvc.xml", "classpath:spring-mybatis.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class BizServiceImplTest extends
AbstractTransactionalJUnit4SpringContextTests{

	@Test
	public void testGetNumberOnDuty() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetNumberHistory() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetEfficiencyHour() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetEfficiencyDay() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetEfficiencyHistory() {
		fail("Not yet implemented");
	}

}
