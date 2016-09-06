<<<<<<< HEAD
package com.jd.pims.pem.dao;

import java.util.LinkedList;

import com.jd.pims.comm.IBaseDao;
import com.jd.pims.pem.model.LabourOndutyState;

public interface LabourOndutyDao extends IBaseDao{

	LinkedList<LabourOndutyState> getNumberOnDuty(String cuId);

}
=======
package com.jd.pims.pem.dao;

import com.jd.pims.comm.IBaseDao;

public interface LabourOndutyDao extends IBaseDao{

}
>>>>>>> d732debe3864b9f328caa8e45b80d5f7bf5cb4bb
