package com.jd.pims;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.jd.pims.pem.controller.AppControllerTest;
import com.jd.pims.pem.service.impl.BizServiceImplTest;
import com.jd.pims.user.controller.UserControllerTest;

@RunWith(Suite.class)
@SuiteClasses({ BizServiceImplTest.class, UserControllerTest.class,
		AppControllerTest.class })
public class AllTests {

}
