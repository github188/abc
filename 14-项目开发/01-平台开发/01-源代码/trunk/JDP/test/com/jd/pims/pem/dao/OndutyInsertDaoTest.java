package com.jd.pims.pem.dao;

import static org.junit.Assert.*;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.jd.pims.pem.model.LabourOnduty;

@ContextConfiguration(locations = { "classpath:spring.xml",
		"classpath:spring-mvc.xml", "classpath:spring-mybatis.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class OndutyInsertDaoTest extends
		AbstractTransactionalJUnit4SpringContextTests {

	private static Set<String> tasks = new HashSet<String>();
	private static final Logger logger = Logger
			.getLogger(OndutyInsertDaoTest.class);

	private ExecutorService threadPool;

	private static String[] cuIds = { "8ae5a2553c6f42be013c6f54661b0319",
			"8ae5a2553c6f42be013c6f54661b0320",
			"8ae5a2553c6f42be013c6f54661b0321",
			"8ae5a2553c6f42be013c6f54661b0322",
			"8ae5a2553c6f42be013c6f54661b0323",
			"8ae5a2553c6f42be013c6f54661b0324",
			"8ae5a2553c6f42be013c6f54661b0325",
			"8ae5a2553c6f42be013c6f54661b0326",
			"8ae5a2553c6f42be013c6f54661b0327",
			"8ae5a2553c6f42be013c6f54661b0328",
			"8ae5a2553c6f42be013c6f54661b0329",
			"8ae5a2553c6f42be013c6f54661b0330",
			"8ae5a2553c6f42be013c6f54661b0331",
			"8ae5a2553c6f42be013c6f54661b0332",
			"8ae5a2553c6f42be013c6f54661b0333",
			"8ae5a2553c6f42be013c6f54661b0334",
			"8ae5a2553c6f42be013c6f54661b0335",
			"8ae5a2553c6f42be013c6f54661b0336",
			"8ae5a2553c6f42be013c6f54661b0337",
			"8ae5a2553c6f42be013c6f54661b0338",
			"8ae5a2553c6f42be013c6f54661b0339",
			"8ae5a2553c6f42be013c6f54661b0340",
			"8ae5a2553c6f42be013c6f54661b0341",
			"8ae5a2553c6f42be013c6f54661b0342",
			"8ae5a2553c6f42be013c6f54661b0343",
			"8ae5a2553c6f42be013c6f54661b0344",
			"8ae5a2553c6f42be013c6f54661b0345",
			"8ae5a2553c6f42be013c6f54661b0346",
			"8ae5a2553c6f42be013c6f54661b0347",
			"8ae5a2553c6f42be013c6f54661b0348",
			"8ae5a2553c6f42be013c6f54661b0349",
			"8ae5a2553c6f42be013c6f54661b0350",
			"8ae5a2553c6f42be013c6f54661b0351",
			"8ae5a2553c6f42be013c6f54661b0352",
			"8ae5a2553c6f42be013c6f54661b0353",
			"8ae5a2553c6f42be013c6f54661b0354",
			"8ae5a2553c6f42be013c6f54661b0355",
			"8ae5a2553c6f42be013c6f54661b0356",
			"8ae5a2553c6f42be013c6f54661b0357",
			"8ae5a2553c6f42be013c6f54661b0358" };
	@Autowired
	private OndutyInsertDao dao;

	private static synchronized void control(String key, boolean added) {
		if (added) {
			tasks.add(key);
		} else {
			tasks.remove(key);
		}

	}

	@Test
	public void testInsert() {

		// final ThreadFactory threadFactory = new ThreadFactoryBuilder()
		// .setNameFormat("OndutyInsert-%d")
		// .setDaemon(true)
		// .build();

		threadPool = Executors.newFixedThreadPool(10);

		for (String id : cuIds) {
			control(id, true);
			threadPool.execute(new InsertWorker(this.dao, id, new Date()));
		}
		logger.info("------------------- " + tasks.size());
		// Assert.assertTrue("",true);
		// 通过线程并发插件一非万条记录
		while (tasks.size() > 0) {
			logger.info("------------------- " + tasks.size());
		}
	}

	class InsertWorker implements Runnable {

		private String cuId;
		private Date startDate;
		private Random random = new Random();
		private OndutyInsertDao dao;

		public InsertWorker(OndutyInsertDao dao, String cuId, Date startDate) {
			this.cuId = cuId;
			this.startDate = startDate;
			this.dao = dao;
		}

		/**
		 * 插入1000万条记录
		 */
		@Override
		public void run() {
			LabourOnduty lod = null;
			// TODO Auto-generated method stub
			for (long i = 0; i < 1000000; i++) {
				try {
					Calendar currentTime = Calendar.getInstance();
					currentTime.setTime(startDate);
					currentTime.add(Calendar.DATE, random.nextInt(100));
					lod = new LabourOnduty();
					String id = UUID.randomUUID().toString().replace("-", "");
					lod.setId(id);
					lod.setCuId(cuId);
					lod.setBizDate(currentTime.getTime());
					lod.setHour(random.nextInt(24));
					lod.setPersonType(random.nextInt(5) + "");
					lod.setQuantityOnduty(random.nextInt(200));
					lod.setCreateTime(startDate);
					lod.setTiming(new Time(random.nextInt(24), random
							.nextInt(59), random.nextInt(59)));
					this.dao.insert(lod);
					logger.info(lod.toString());
					// try {
					// Thread.sleep(10);
					// } catch (Exception e) {
					// logger.debug("Thread.sleep",e);
					// e.printStackTrace();
					// }
				} catch (Exception e) {
					logger.debug("run", e);
					e.printStackTrace();
				}
			}
			control(cuId, false);
		}

	}
}
