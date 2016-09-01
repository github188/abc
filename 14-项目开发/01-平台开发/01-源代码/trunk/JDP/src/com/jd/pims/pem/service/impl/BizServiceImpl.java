package com.jd.pims.pem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jd.pims.pem.dao.LabourEfficiencyDao;
import com.jd.pims.pem.dao.LabourOndutyDao;
import com.jd.pims.pem.dao.OrderQuantityDao;
import com.jd.pims.pem.service.AbsService;

@Service("bizServiceImpl")
public class BizServiceImpl extends AbsService {
	@Autowired
	private LabourOndutyDao labourOndutyDao;

	@Autowired
	private LabourEfficiencyDao labourEfficiencyDao;
	
	@Autowired
	private OrderQuantityDao orderQuantityDao;
	
	

}
