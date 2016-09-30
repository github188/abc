package com.jd.pims.user.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.jd.pims.comm.IBaseDao;
import com.jd.pims.user.model.ControlUnit;
import com.jd.pims.user.model.Employee;
import com.jd.pims.user.model.User;

public interface UserDao extends IBaseDao{
	
	/**
	 * 根据用户帐号取用户档案信息
	 * @param account 用户帐号
	 * @return
	 */
	User getUserByAccount(String account);
	
	/**
	 * 根据员工编号取员工信息
	 * @param id 员工id
	 * @return
	 */
	Employee getEmployeeById(String id);
	
	/**
	 * 取控制单元列表
	 * @return
	 */
	List<ControlUnit> getOrganizations();
	
	/**
	 * 取父节点编写为parentId的子控制单元列表 
	 * @param parentId
	 * @return
	 */
	List<ControlUnit> getSubOrganizations(String parentId);
	
	/**
	 * 获取控制单元 
	 * @param cuId
	 * @return
	 */
	ControlUnit findOrganization(String cuId);
	
	/**
	 * 取根节点的组织架构 
	 * @return
	 */
	ControlUnit findRootOrganization();
	
	/**
	 * 搜索相似人员档案信息
	 * @param inputStr
	 * @return
	 */
	List<Employee> searchEmployee(@Param("inputStr")String inputStr);

	List<Map<String, Object>> getAreaList(@Param("name")String name);
	
	Integer getSubOrganizationSize(@Param("parentId")String parentId);
	
	List<Map<String, Object>> getCurrentTimeAreaForChart(@Param("name")String name);
}