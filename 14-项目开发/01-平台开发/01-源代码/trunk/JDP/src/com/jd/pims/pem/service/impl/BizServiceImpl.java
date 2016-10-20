package com.jd.pims.pem.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jd.pims.pem.dao.LabourEfficiencyDao;
import com.jd.pims.pem.dao.LabourOndutyDao;
import com.jd.pims.pem.dao.OrderQuantityDao;
import com.jd.pims.pem.dao.ReportDao;
import com.jd.pims.pem.model.LabourEfficiency;
import com.jd.pims.pem.model.LabourOnduty;
import com.jd.pims.pem.model.LabourOndutyDayState;
import com.jd.pims.pem.model.LabourOndutyState;
import com.jd.pims.pem.service.IBizService;
import com.jd.pims.user.dao.UserDao;
import com.jd.pims.user.model.ControlUnit;

@Service("bizServiceImpl")
public class BizServiceImpl implements IBizService {

	private static final Logger logger = Logger.getLogger(BizServiceImpl.class
			.getName());

	@Autowired
	private LabourEfficiencyDao labourEfficiencyDao;
	@Autowired
	private LabourOndutyDao labourOndutyDao;
	@Autowired
	private OrderQuantityDao orderQuantityDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private ReportDao reportDao;

	private SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd");

	@Override
	public LabourOndutyState getNumberOnDuty(String cuId) {
		return this.getNumberOnDuty(cuId, new Date());
	}

	@Override
	public LabourOndutyState getNumberOnDuty(String cuId, Date date) {
		// TODO Auto-generated method stub
		LabourOndutyState state = new LabourOndutyState();
		state.setCuId(cuId);
		ControlUnit cu = userDao.findOrganization(cuId);
		state.setCuName(cu.getCuName());
		List<LabourOnduty> list;
		int beginTime=getBeginTime();
		int endTime=beginTime;
		beginTime--;
		list = labourOndutyDao.getCurrentTimeLabourOnduty(sFormat.format(date),
				(beginTime<10?"0":"")+beginTime+":00:00",(endTime<10?"0":"")+endTime+":00:00",cu.getFullPath());
		for (LabourOnduty rec : list) {
			if (rec.getPersonType().equals("1") ) {
				state.setNumEmp((state.getNumEmp() == null ? 0 : state
						.getNumEmp()) + rec.getQuantityOnduty());
			} else if (rec.getPersonType().equals("2")
					|| rec.getPersonType().equals("3")
					|| rec.getPersonType().equals("4")) {
				state.setNumTemp((state.getNumTemp() == null ? 0 : state
						.getNumTemp()) + rec.getQuantityOnduty());
			} else if (rec.getPersonType().equals("5")) {
				state.setNumOther((state.getNumOther() == null ? 0 : state
						.getNumOther()) +rec.getQuantityOnduty());
			}
		}

		return state;
	}

	@Override
	public List<LabourOndutyState> getNumberHistory(String cuId,
			Date startDate, Date endDate, String interval) {
		ControlUnit cu = userDao.findOrganization(cuId);

		List<LabourOndutyDayState> list = labourOndutyDao
				.getHistoryLabourOnduty(sFormat.format(startDate),
						sFormat.format(endDate), cu.getFullPath());
		Map<String, LabourOndutyState> map = new HashMap<String, LabourOndutyState>();
		for (LabourOndutyDayState rec : list) {
			LabourOndutyState state = null;
			if (map.containsKey(sFormat.format(rec.getBizDate()))) {
				state = map.get(sFormat.format(rec.getBizDate()));
			} else {
				state = new LabourOndutyState();
				state.setCuId(cuId);
				state.setCuName(cu.getCuName());
				state.setDayTime(sFormat.format(rec.getBizDate()));
				map.put(sFormat.format(rec.getBizDate()), state);
			}

			if (rec.getPersonType().equals("1")) {
				state.setNumEmp(rec.getAvgQuantity());
			} else if (rec.getPersonType().equals("2")
					|| rec.getPersonType().equals("3")
					|| rec.getPersonType().equals("4")) {
				state.setNumTemp((state.getNumTemp() == null ? 0 : state
						.getNumTemp()) + rec.getAvgQuantity());
			} else if (rec.getPersonType().equals("5")) {
				state.setNumOther(rec.getAvgQuantity());
			}
		}
		return new ArrayList<LabourOndutyState>(map.values());
	}

	@Override
	public List<LabourEfficiency> getEfficiencyHistory(String cuId,
			Date startDate, Date endDate) {
		ControlUnit cu = userDao.findOrganization(cuId);
		return labourEfficiencyDao.getHistoryLabourEfficiency(
				sFormat.format(startDate), sFormat.format(endDate),
				cu.getFullPath());

	}

	@Override
	public LabourEfficiency getTimePeriodEfficience(String cuId, Date bizDate,
			Integer timePeriod) {
		ControlUnit cu = userDao.findOrganization(cuId);
		// 如果timePeriod为空，默认当前一小时
		if (timePeriod == null) {
			timePeriod = getBeginTime();
		}
		int beginTime=timePeriod-1;
		int endTime=timePeriod;
		//取符合条件的人效记录（每个分拣中心一条记录）
		LabourEfficiency result = labourEfficiencyDao.getLabourEfficiency(
				sFormat.format(bizDate), timePeriod, cu.getFullPath(),(beginTime<10?"0":"")+beginTime+":00:00",(endTime<10?"0":"")+endTime+":00:00");
		if(result==null){
			result=new LabourEfficiency();
		}
		result.setCuId(cuId);
		result.setCuName(cu.getCuName());
		if(result.getNumberOnduty()!=0){
			result.setEfficiency(result.getOrderQuantity()/(double)result.getNumberOnduty());
		}
		return result;
	}

	private int getBeginTime(){
		Calendar currentTime = Calendar.getInstance();
		currentTime.setTime(new Date());
		int timePeriod = currentTime.get(Calendar.HOUR_OF_DAY) ;
		if (timePeriod ==0) {
			timePeriod = 24;
		}
		return timePeriod;
	}

	@Override
	public List<Map<String, Object>> queryYydata(String[] inputs,int startpages) {
		
		String[] inputss = new String[]{"","","",""};
		for (int i = 0; i < inputs.length; i++) {
			if(inputs[i].equals("区域")){
				inputss[0]=inputs[i+1];
				i++;
			}else if (inputs[i].equals("开始时间")) {
				inputss[1]=inputs[i+1];
				i++;
			}else if (inputs[i].equals("结束时间")) {
				inputss[2]=inputs[i+1];
				i++;
			}else if (inputs[i].equals("分拣场地")) {
				inputss[3]=inputs[i+1];
				i++;
			}
		}
		
		List<Map<String, Object>> datas = reportDao.queryYydata(inputss[0],inputss[1].isEmpty()?"1970-01-01":inputss[1],inputss[2].isEmpty()?"2050-01-01":inputss[2],inputss[3],startpages*9,startpages*9+8);
		
		List<Map<String, Object>> response = new ArrayList<>();
		
		for (Map<String, Object> data : datas) {
			String PARENT_NAME = data.get("PARENT_NAME").toString();//区域
			String NAME = data.get("NAME").toString();//分拣中心名称
			String QUANTITY_ONDUTY = data.get("QUANTITY_ONDUTY").toString();//在岗人数
			String ORDER_QUANTITY = data.get("ORDER_QUANTITY").toString();//订单总数
			String CU_ID = data.get("CU_ID").toString();//订单总数
			
			HashMap<String, Object> map = new HashMap<>();
			map.put("areaName", PARENT_NAME);
			map.put("pimsName", NAME);
			map.put("quantityOnduty", QUANTITY_ONDUTY);
			map.put("orderQuantity", ORDER_QUANTITY);
			
			Map<String, Object> map1= reportDao.queryavgefficiency(CU_ID,inputss[1].isEmpty()?"1970-01-01":inputss[1],inputss[2].isEmpty()?"2050-01-01":inputss[2]);
			map.put("avgEfficiency", map1.get("AVG_EFFICIENCY"));
			
			List<Map<String, Object>> map2= reportDao.queryOnduty(CU_ID,inputss[1].isEmpty()?"1970-01-01":inputss[1],inputss[2].isEmpty()?"2050-01-01":inputss[2]);
			Integer normal = null;
			Integer notnomal= null;
			Integer other= null;
			for (Map<String, Object> data1 : map2) {
				if("1".equals(data1.get("PERSON_TYPE"))){
					normal = data1.get("QUANTITY_ONDUTY")==null?0:(Integer)data1.get("QUANTITY_ONDUTY"); //正式工
				}
				if("2".equals(data1.get("PERSON_TYPE"))){
					notnomal =  data1.get("QUANTITY_ONDUTY")==null?0:(Integer)data1.get("QUANTITY_ONDUTY"); //临时工
				}
				if("5".equals(data1.get("PERSON_TYPE"))){
					other =   data1.get("QUANTITY_ONDUTY")==null?0:(Integer)data1.get("QUANTITY_ONDUTY");//其他工
				}
			}
			map.put("normal", normal.toString());
			map.put("notnomal", notnomal.toString());
			map.put("other", other.toString());
			map.put("percent", normal.toString().equals("0")?0:(int)((float)normal/(float)(normal+notnomal)*100)+"%");			
			response.add(map);
			
		}
		
		return response;
	}
}