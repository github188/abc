package com.jd.pims.pem.dao;

import java.util.LinkedList;

import com.jd.pims.comm.IBaseDao;
import com.jd.pims.pem.model.LabourOndutyState;

public interface LabourOndutyDao extends IBaseDao{

	LinkedList<LabourOndutyState> getNumberOnDuty(String cuId);

}
