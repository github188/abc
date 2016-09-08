package com.jd.pims.pem.service.impl;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jd.pims.pem.model.LabourOndutyState;
import com.jd.pims.pem.service.IBizService;

@ContextConfiguration(locations = { "classpath:spring.xml",
		"classpath:spring-mvc.xml", "classpath:spring-mybatis.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class BizServiceImplTest extends
		AbstractTransactionalJUnit4SpringContextTests {
	private static final Logger logger = Logger
			.getLogger(BizServiceImplTest.class.getName());

	@Autowired
	private IBizService pemService;

	@Test
	public void testGetNumberOnDuty() {
		Calendar c1 = Calendar.getInstance();
		c1.setTime(new Date());
		c1.set(2016, 9, 7);
		LabourOndutyState state = pemService
				.getNumberOnDuty("11111111111111111111111111111111",c1.getTime());
		Assert.assertTrue(state.toString(), state != null);
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
