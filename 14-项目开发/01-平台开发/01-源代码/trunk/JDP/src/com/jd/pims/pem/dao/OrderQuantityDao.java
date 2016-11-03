package com.jd.pims.pem.dao;

import org.apache.ibatis.annotations.Param;

import com.jd.pims.comm.IBaseDao;
import com.jd.pims.pem.model.OrderQuantity;

public interface OrderQuantityDao extends IBaseDao{
	/**
	 * 取小时订单量
	 * @param bizDate
	 * @param timePeriod
	 * @param fullPath
	 * @return
	 */
	OrderQuantity getHourOrderQuantiy(@Param("bizDate") String bizDate,
			@Param("timePeriod") Integer timePeriod,
			@Param("fullPath") String fullPath);
}
