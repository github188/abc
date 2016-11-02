package com.jd.pims.user.service.impl;

import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jd.pims.comm.ControlUnitCache;
import com.jd.pims.comm.LoginInfoCache;
import com.jd.pims.comm.PIMSException;
import com.jd.pims.user.dao.UserDao;
import com.jd.pims.user.model.ControlUnit;
import com.jd.pims.user.model.Employee;
import com.jd.pims.user.model.Person;
import com.jd.pims.user.model.User;
import com.jd.pims.user.model.UserRole;
import com.jd.pims.user.service.IUserService;
import com.jd.pims.util.StringUtil;

import jxl.Sheet;
import jxl.Workbook;

@Service("userService")
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserDao userDao;

	private static final Logger logger = Logger.getLogger(UserServiceImpl.class
			.getName());

	@Override
	public Employee login(String account, String password) throws PIMSException {
		User user = userDao.getUserByAccount(account);
		if (user == null) {
			throw new PIMSException(1, "系统不存在用户：" + account);
		}

		if (!StringUtil.md5(password).equals(user.getPassword())) {
			throw new PIMSException(2, "用户密码不正确");
		}
		Employee emp = userDao.getEmployeeById(user.getPersonId());
		if(emp==null || !emp.getStatus().equals("NORMAL")){
			throw new PIMSException(3,"用户绑定的员工不存在，请确认！");
		}
		LoginInfoCache.add(user, emp);
		return emp;
	}

	@Override
	public void logout(String empId) {

		Employee emp = LoginInfoCache.getLoginEmployee(empId);
		if (emp != null) {
			logger.debug("员工" + emp.getEmpName() + "退出APP登录！");
		}
		LoginInfoCache.remove(empId);
	}

	@Override
	public List<ControlUnit> getOrganizations() {

		return userDao.getOrganizations();

	}

	@Override
	public List<ControlUnit> getSubOrganizations(String parentId) {
		return userDao.getSubOrganizations(parentId);
	}

	@Override
	public ControlUnit findOrganization(String cuId) {
		ControlUnit cu=ControlUnitCache.get(cuId);
		if(cu==null){
			cu=userDao.findOrganization(cuId);
			if(cu!=null){
				ControlUnitCache.add(cu);
			}
		}
		return cu;
	}

	@Override
	public List<Employee> searchEmployee(String inputStr) {
		// TODO Auto-generated method stub
		return userDao.searchEmployee(inputStr);
	}
	
	@Override
	public ControlUnit findRootOrganization() {
		// TODO Auto-generated method stub
		return userDao.findRootOrganization();
	}
	
	@Override
	public String createAccount(InputStream inputStream) {
		
		List<Map<String, Object>> controlunits = userDao.queryControlunit();
		Map<String, String > map1 = new HashMap<>();
		for (Map<String, Object> map : controlunits) {
			map1.put(map.get("NAME")+"", map.get("CU_ID")+","+ map.get("ORG_ID"));
		}
		
		Workbook book;
		try {
			book = Workbook.getWorkbook(inputStream);
			// 获得第一个工作表对象
			Sheet sheet = book.getSheet(0);
			// 得到第一列第一行的单元格
			int row = sheet.getRows();
			int col = sheet.getColumns();
			List<User> users = new ArrayList<>();
			List<UserRole> userroles = new ArrayList<>();
			List<Person> persons = new ArrayList<>();
			for(int i =1;i<row ; i++){
				String controlunit =sheet.getCell(0, i).getContents();
				String name =sheet.getCell(1, i).getContents();
				String sex =sheet.getCell(2, i).getContents();
				String tel =sheet.getCell(3, i).getContents();
				String email =sheet.getCell(4, i).getContents();
				String worktype =sheet.getCell(5, i).getContents();
				
				User u = userDao.getUserByAccount(name);
				if(u==null){
					User user = new User();
					String userId = UUID.randomUUID().toString().replace("-", "");
					String personId = UUID.randomUUID().toString().replace("-", "");
					user.setId(userId);
					user.setUserName(name);
					user.setPassword("e10adc3949ba59abbe56e057f20f883e");
					user.setPersonId(personId);
					user.setUserType("SYSUSER");
					user.setStatus("NORMAL");
					user.setControlunitid(map1.get(controlunit).split(",")[0]);
					users.add(user);
					
					UserRole userRole = new UserRole();
					userRole.setUserId(userId);
					userRole.setRoleId("11111111111111111111111111111111");
					userroles.add(userRole);
					
					Person person = new Person();
					person.setId(personId);
					person.setPerson_code(UUID.randomUUID().toString().replace("-", "").substring(0, 8));
					person.setPerson_name(name);
					person.setOrg_id(map1.get(controlunit).split(",")[1]);
					person.setIs_clerk(1);
					person.setSex((sex==null)?"UNKNOW":"男".equals(sex)?"MALE":"FEMALE");
					person.setTelephone(tel);
					person.setEmail(email);
					person.setCard_type("GENERIDENT");
					person.setStatus("NORMAL");
					person.setControlunitid(map1.get(controlunit).split(",")[0]);
					person.setIs_edit(1);
					person.setWork_type(worktype);
					
					persons.add(person);
				}
			}
			if(users!=null&&users.size()!=0){
				userDao.createUser(users);
			}
			if(userroles!=null&&userroles.size()!=0){
				userDao.createUserRoles(userroles);
			}
			if(persons!=null&&persons.size()!=0){
				userDao.createpersons(persons);
			}
			
			book.close();
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		} 
		
		return "success";
	}

}
