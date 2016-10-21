package com.jd.pims.pem.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.jd.pims.comm.IBaseDao;

public interface ReportDao extends IBaseDao {
	List<Map<String, Object>> queryYydata(@Param("area")String area,
			@Param("starttime")String starttime,
			@Param("endtime")String endtime,
			@Param("pimsname")String pimsname,
			@Param("startpages")int startpages,
			@Param("pageSize")int pageSize);
	
	String queryyyallpages(@Param("area")String area,
			@Param("starttime")String starttime,
			@Param("endtime")String endtime,
			@Param("pimsname")String pimsname);
	
	
	Map<String, Object> queryavgefficiency(@Param("cuid")String cuid,
			@Param("starttime")String starttime,
			@Param("endtime")String endtime);
	
	List<Map<String, Object>> queryOnduty(@Param("cuid")String cuid,
			@Param("starttime")String starttime,
			@Param("endtime")String endtime);
	
	List<Map<String, Object>> queryAllYydata(@Param("area")String area,
			@Param("starttime")String starttime,
			@Param("endtime")String endtime,
			@Param("pimsname")String pimsname);
}
