package com.jd.pims.pem.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

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
	public List<Map<String, Object>> queryYydata(String[] inputs,int startpages,int endpages) {
		
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
		
		List<Map<String, Object>> datas = reportDao.queryYydata(inputss[0],inputss[1].isEmpty()?"1970-01-01":inputss[1],inputss[2].isEmpty()?"2050-01-01":inputss[2],inputss[3],startpages*8,endpages);
		
		List<Map<String, Object>> response = new ArrayList<>();
		
		for (Map<String, Object> data : datas) {
			String PARENT_NAME = data.get("PARENT_NAME").toString();//区域
			String NAME = data.get("NAME").toString();//分拣中心名称
			String QUANTITY_ONDUTY = data.get("QUANTITY_ONDUTY").toString();//在岗人数
			String ORDER_QUANTITY = data.get("ORDER_QUANTITY")==null?"":data.get("ORDER_QUANTITY").toString();//订单总数
			String CU_ID = data.get("CU_ID").toString();//订单总数
			
			HashMap<String, Object> map = new HashMap<>();
			map.put("areaName", PARENT_NAME);
			map.put("pimsName", NAME);
			map.put("quantityOnduty", QUANTITY_ONDUTY);
			map.put("orderQuantity", ORDER_QUANTITY);
			
			Map<String, Object> map1= reportDao.queryavgefficiency(CU_ID,inputss[1].isEmpty()?"1970-01-01":inputss[1],inputss[2].isEmpty()?"2050-01-01":inputss[2]);
			if(map1!=null && map1.size()!=0){
				map.put("avgEfficiency", map1.get("AVG_EFFICIENCY"));
			}
			List<Map<String, Object>> map2= reportDao.queryOnduty(CU_ID,inputss[1].isEmpty()?"1970-01-01":inputss[1],inputss[2].isEmpty()?"2050-01-01":inputss[2]);
			Integer normal = 0;
			Integer notnomal= 0;
			Integer other= 0;
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
			String allpages= reportDao.queryyyallpages(PARENT_NAME, inputss[1].isEmpty()?"1970-01-01":inputss[1], inputss[2].isEmpty()?"2050-01-01":inputss[2], NAME);
			map.put("allpages", Integer.parseInt(allpages)/8+1);
			response.add(map);
			
		}
		
		return response;
	}

	@Override
	public String yydata(String[] inputs, HttpServletRequest request) {
		try {

			String[] inputss = new String[] { "", "", "", "" };
			for (int i = 0; i < inputs.length; i++) {
				if (inputs[i].equals("区域")) {
					inputss[0] = inputs[i + 1];
					i++;
				} else if (inputs[i].equals("开始时间")) {
					inputss[1] = inputs[i + 1];
					i++;
				} else if (inputs[i].equals("结束时间")) {
					inputss[2] = inputs[i + 1];
					i++;
				} else if (inputs[i].equals("分拣场地")) {
					inputss[3] = inputs[i + 1];
					i++;
				}
			}

			List<Map<String, Object>> datas = reportDao.queryAllYydata(inputss[0],
					inputss[1].isEmpty() ? "1970-01-01" : inputss[1], inputss[2].isEmpty() ? "2050-01-01" : inputss[2],
					inputss[3]);

			List<Map<String, Object>> response = new ArrayList<>();

			for (Map<String, Object> data : datas) {
				String PARENT_NAME = data.get("PARENT_NAME").toString();// 区域
				String NAME = data.get("NAME").toString();// 分拣中心名称
				String QUANTITY_ONDUTY = data.get("QUANTITY_ONDUTY").toString();// 在岗人数
				String ORDER_QUANTITY = data.get("ORDER_QUANTITY") == null ? "" : data.get("ORDER_QUANTITY").toString();// 订单总数
				String CU_ID = data.get("CU_ID").toString();// 订单总数

				HashMap<String, Object> map = new HashMap<>();
				map.put("areaName", PARENT_NAME);
				map.put("pimsName", NAME);
				map.put("quantityOnduty", QUANTITY_ONDUTY);
				map.put("orderQuantity", ORDER_QUANTITY);

				Map<String, Object> map1 = reportDao.queryavgefficiency(CU_ID,
						inputss[1].isEmpty() ? "1970-01-01" : inputss[1],
						inputss[2].isEmpty() ? "2050-01-01" : inputss[2]);
				if (map1 != null && map1.size() != 0) {
					map.put("avgEfficiency", map1.get("AVG_EFFICIENCY"));
				}
				List<Map<String, Object>> map2 = reportDao.queryOnduty(CU_ID,
						inputss[1].isEmpty() ? "1970-01-01" : inputss[1],
						inputss[2].isEmpty() ? "2050-01-01" : inputss[2]);
				Integer normal = 0;
				Integer notnomal = 0;
				Integer other = 0;
				for (Map<String, Object> data1 : map2) {
					if ("1".equals(data1.get("PERSON_TYPE"))) {
						normal = data1.get("QUANTITY_ONDUTY") == null ? 0 : (Integer) data1.get("QUANTITY_ONDUTY"); // 正式工
					}
					if ("2".equals(data1.get("PERSON_TYPE"))) {
						notnomal = data1.get("QUANTITY_ONDUTY") == null ? 0 : (Integer) data1.get("QUANTITY_ONDUTY"); // 临时工
					}
					if ("5".equals(data1.get("PERSON_TYPE"))) {
						other = data1.get("QUANTITY_ONDUTY") == null ? 0 : (Integer) data1.get("QUANTITY_ONDUTY");// 其他工
					}
				}
				map.put("normal", normal.toString());
				map.put("notnomal", notnomal.toString());
				map.put("other", other.toString());
				map.put("percent", normal.toString().equals("0") ? 0
						: (int) ((float) normal / (float) (normal + notnomal) * 100) + "%");
				response.add(map);

			}

			String path = "excels\\" + new Date().getTime() + ".xls";
			FileOutputStream os;
			os = new FileOutputStream(
					new File(request.getSession().getServletContext().getRealPath("/") + path) + "\\");

			// 创建工作薄
			WritableWorkbook workbook = Workbook.createWorkbook(os);
			// 创建新的一页
			WritableSheet sheet = workbook.createSheet("First Sheet", 0);
			Label title1 = new Label(0, 0, "大区");
			sheet.addCell(title1);
			Label title2 = new Label(1, 0, "分拣场地名称");
			sheet.addCell(title2);
			Label title3 = new Label(2, 0, "操作总单量");
			sheet.addCell(title3);
			Label title4 = new Label(3, 0, "出勤总人数");
			sheet.addCell(title4);
			Label title5 = new Label(4, 0, "平均人效");
			sheet.addCell(title5);
			Label title6 = new Label(5, 0, "正式工数量");
			sheet.addCell(title6);
			Label title7 = new Label(6, 0, "非正式工数量");
			sheet.addCell(title7);
			Label title8 = new Label(7, 0, "其他人员数量");
			sheet.addCell(title8);
			Label title9 = new Label(8, 0, "正式工占比");
			sheet.addCell(title9);
			int i=1;//标志位
			for (Map<String, Object> map : response) {
				// 创建要显示的内容,创建一个单元格，第一个参数为列坐标，第二个参数为行坐标，第三个参数为内容
				Label areaName = new Label(0, i, map.get("areaName")==null?"":map.get("areaName").toString());
				sheet.addCell(areaName);
				Label pimsName = new Label(1, i, map.get("pimsName")==null?"":map.get("pimsName").toString());
				sheet.addCell(pimsName);
				Label orderQuantity = new Label(2, i, map.get("orderQuantity")==null?"":map.get("orderQuantity").toString());
				sheet.addCell(orderQuantity);
				Label quantityOnduty = new Label(3, i, map.get("quantityOnduty")==null?"":map.get("quantityOnduty").toString());
				sheet.addCell(quantityOnduty);
				Label avgEfficiency = new Label(4, i, map.get("avgEfficiency")==null?"":map.get("avgEfficiency").toString());
				sheet.addCell(avgEfficiency);
				Label normal = new Label(5, i, map.get("normal")==null?"":map.get("normal").toString());
				sheet.addCell(normal);
				Label notnomal = new Label(6, i, map.get("notnomal")==null?"":map.get("notnomal").toString());
				sheet.addCell(notnomal);
				Label other = new Label(7, i, map.get("other")==null?"":map.get("other").toString());
				sheet.addCell(other);
				Label percent = new Label(8, i, map.get("percent")==null?"":map.get("percent").toString());
				sheet.addCell(percent);
				
				i++;
			}

			// 把创建的内容写入到输出流中，并关闭输出流
			workbook.write();
			workbook.close();
			os.close();
			return path.replace("\\", "/");
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

}