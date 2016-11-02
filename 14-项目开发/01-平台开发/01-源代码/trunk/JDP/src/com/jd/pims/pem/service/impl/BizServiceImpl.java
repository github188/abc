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
		//取时间跨度，当前时间到前30分钟这一时段
		String[] timeSpan=getTimeSpan(null);
		//beginTime--;
		list = labourOndutyDao.getCurrentTimeLabourOnduty(sFormat.format(date),
				timeSpan[0],timeSpan[1],cu.getFullPath());
		Integer qty=0;
		logger.debug("符合记录数--->"+list.size());
		for (LabourOnduty rec : list) {
			qty=rec.getQuantityOnduty()==null?0:rec.getQuantityOnduty();
			logger.debug("记录信息：\n"+rec);
			if (rec.getPersonType().equals("1") ) {
				state.setNumEmp((state.getNumEmp() == null ? 0 : state
						.getNumEmp()) + qty);
			} else if (rec.getPersonType().equals("2")
					|| rec.getPersonType().equals("3")
					|| rec.getPersonType().equals("4")) {
				state.setNumTemp((state.getNumTemp() == null ? 0 : state
						.getNumTemp()) + qty);
			} else if (rec.getPersonType().equals("5")) {
				state.setNumOther((state.getNumOther() == null ? 0 : state
						.getNumOther()) +qty);
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
		
		String[] timeSpan=getTimeSpan(timePeriod);
		//取符合条件的人效记录（每个分拣中心一条记录）
		LabourEfficiency result = labourEfficiencyDao.getLabourEfficiency(
				sFormat.format(bizDate), timePeriod, cu.getFullPath(),timeSpan[0],timeSpan[1]);
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
	
	private String[] getTimeSpan(Integer timePeriod){
		SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
		Calendar currentTime = Calendar.getInstance();
		currentTime.setTime(new Date());
		if(timePeriod!=null){
			currentTime.set(Calendar.HOUR, timePeriod);
		}
		String[] times=new String[]{"",""};
		times[1]=df.format(currentTime.getTime());
		currentTime.set(Calendar.MINUTE, currentTime.get(Calendar.MINUTE)-30);
		times[0]=df.format(currentTime.getTime());
		
		return times;
	}

	@Override
	public List<Map<String, Object>> queryYydata(String[] inputs,int startpages,int pageSize) {
		for (int i = 0; i < inputs.length; i++) {
			if("undefined".equals(inputs[i].trim())){
				inputs[i]="";
			}
		}
		StringBuffer sBuffer = new StringBuffer();
		StringBuffer sBuffer1 = new StringBuffer();
		String[] inputss = new String[]{"","","",""};
		for (int i = 0; i < inputs.length; i++) {
			if(inputs[i].trim().equals("区域")){
				inputss[0]=inputs[i+1].trim();
				i++;
			}else if (inputs[i].trim().equals("开始时间")) {
				String[] sss = inputs[i+1].split("-");
				for (int j = 0;j < sss.length; j++) {
					if(sss[j].length()<2){
						sss[j]="0"+sss[j];
					}
				}
				for (int j = 0; j < sss.length; j++) {
					sBuffer.append(sss[j]+"-");
				}
				inputss[1]=sBuffer.substring(0, sBuffer.length()-2).trim().replace(" ", "-");
				i++;
			}else if (inputs[i].trim().equals("结束时间")) {
				String[] sss = inputs[i+1].split("-");
				for (int j = 0;j < sss.length; j++) {
					if(sss[j].length()<2){
						sss[j]="0"+sss[j];
					}
				}
				for (int j = 0; j < sss.length; j++) {
					sBuffer1.append(sss[j]+"-");
				}
				inputss[2]=sBuffer1.substring(0, sBuffer1.length()-2).trim().replace(" ", "-");
				i++;
			}else if (inputs[i].trim().equals("分拣场地")) {
				inputss[3]=inputs[i+1].trim();
				i++;
			}
		}
		
		List<Map<String, Object>> datas = reportDao.queryYydata(inputss[0],inputss[1].isEmpty()?"1970-01-01-00":inputss[1],inputss[2].isEmpty()?"2050-01-01-00":inputss[2],inputss[3],0,100);
		String counts= Integer.toString(datas.size());
		List<Map<String, Object>> responsedata = datas.subList(startpages*8,(startpages*8+pageSize>Integer.parseInt(counts))?Integer.parseInt(counts):startpages*8+pageSize);
		List<Map<String, Object>> response = new ArrayList<>();
		
		int i =startpages*8+1;
		for (Map<String, Object> data : responsedata) {
			String PARENT_NAME = data.get("PARENT_NAME").toString();//区域
			String NAME = data.get("NAME").toString();//分拣中心名称
			String QUANTITY_ONDUTY = data.get("QUANTITY_ONDUTY")==null?"":data.get("QUANTITY_ONDUTY").toString();//在岗人数
			String ORDER_QUANTITY = data.get("ORDER_QUANTITY")==null?"":data.get("ORDER_QUANTITY").toString();//订单总数
			String CU_ID = data.get("CU_ID").toString();//订单总数
			
			HashMap<String, Object> map = new HashMap<>();
			map.put("areaName", PARENT_NAME);
			map.put("pimsName", NAME);
			map.put("quantityOnduty", QUANTITY_ONDUTY);
			map.put("orderQuantity", ORDER_QUANTITY);
			
			Map<String, Object> map1= reportDao.queryavgefficiency(CU_ID,inputss[1].isEmpty()?"1970-01-01-00":inputss[1],inputss[2].isEmpty()?"2050-01-01-00":inputss[2]);
			if(map1!=null && map1.size()!=0){
				map.put("avgEfficiency", map1.get("AVG_EFFICIENCY"));
			}
			List<Map<String, Object>> map2= reportDao.queryOnduty(CU_ID,inputss[1].isEmpty()?"1970-01-01-00":inputss[1],inputss[2].isEmpty()?"2050-01-01-00":inputss[2]);
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
			map.put("allpages", Integer.parseInt(counts)/9+1);
			map.put("counts", counts);
			map.put("index", i);
			response.add(map);
			i++;
		}
		
		return response;
	}

	@Override
	public String yydata(String[] inputs, HttpServletRequest request) {
		try {
			String[] conditions = inputs;
			for (int i = 0; i < inputs.length; i++) {
				if("undefined".equals(inputs[i].trim())){
					inputs[i]="";
				}
			}
			StringBuffer sBuffer = new StringBuffer();
			StringBuffer sBuffer1 = new StringBuffer();
			String[] inputss = new String[]{"","","",""};
			for (int i = 0; i < inputs.length; i++) {
				if(inputs[i].trim().equals("区域")){
					inputss[0]=inputs[i+1].trim();
					i++;
				}else if (inputs[i].trim().equals("开始时间")) {
					String[] sss = inputs[i+1].split("-");
					for (int j = 0;j < sss.length; j++) {
						if(sss[j].length()<2){
							sss[j]="0"+sss[j];
						}
					}
					for (int j = 0; j < sss.length; j++) {
						sBuffer.append(sss[j]+"-");
					}
					inputss[1]=sBuffer.substring(0, sBuffer.length()-2).trim().replace(" ", "-");
					i++;
				}else if (inputs[i].trim().equals("结束时间")) {
					String[] sss = inputs[i+1].split("-");
					for (int j = 0;j < sss.length; j++) {
						if(sss[j].length()<2){
							sss[j]="0"+sss[j];
						}
					}
					for (int j = 0; j < sss.length; j++) {
						sBuffer1.append(sss[j]+"-");
					}
					inputss[2]=sBuffer1.substring(0, sBuffer1.length()-2).trim().replace(" ", "-");
					i++;
				}else if (inputs[i].trim().equals("分拣场地")) {
					inputss[3]=inputs[i+1].trim();
					i++;
				}
			}

			List<Map<String, Object>> datas = reportDao.queryAllYydata(inputss[0],
					inputss[1].isEmpty() ? "1970-01-01-00" : inputss[1], inputss[2].isEmpty() ? "2050-01-01-00" : inputss[2],
					inputss[3]);

			List<Map<String, Object>> response = new ArrayList<>();

			for (Map<String, Object> data : datas) {
				String PARENT_NAME = data.get("PARENT_NAME").toString();// 区域
				String NAME = data.get("NAME").toString();// 分拣中心名称
				String QUANTITY_ONDUTY = data.get("QUANTITY_ONDUTY")==null?"":data.get("QUANTITY_ONDUTY").toString();// 在岗人数
				String ORDER_QUANTITY = data.get("ORDER_QUANTITY") == null ? "" : data.get("ORDER_QUANTITY").toString();// 订单总数
				String CU_ID = data.get("CU_ID").toString();// 订单总数

				HashMap<String, Object> map = new HashMap<>();
				map.put("areaName", PARENT_NAME);
				map.put("pimsName", NAME);
				map.put("quantityOnduty", QUANTITY_ONDUTY);
				map.put("orderQuantity", ORDER_QUANTITY);

				Map<String, Object> map1 = reportDao.queryavgefficiency(CU_ID,
						inputss[1].isEmpty() ? "1970-01-01-00" : inputss[1],
						inputss[2].isEmpty() ? "2050-01-01-00" : inputss[2]);
				if (map1 != null && map1.size() != 0) {
					map.put("avgEfficiency", map1.get("AVG_EFFICIENCY"));
				}
				List<Map<String, Object>> map2 = reportDao.queryOnduty(CU_ID,
						inputss[1].isEmpty() ? "1970-01-01-00" : inputss[1],
						inputss[2].isEmpty() ? "2050-01-01-00" : inputss[2]);
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

			String path = "excels"+System.getProperty("file.separator") + new Date().getTime() + ".xls";
			System.out.println(request.getSession().getServletContext().getRealPath("") +System.getProperty("file.separator")+ path);
			FileOutputStream os;
			os = new FileOutputStream(
					new File(request.getSession().getServletContext().getRealPath("") +System.getProperty("file.separator")+ path));
			System.out.println(request.getSession().getServletContext().getRealPath("") +System.getProperty("file.separator")+ path);
			// 创建工作薄
			WritableWorkbook workbook = Workbook.createWorkbook(os);
			// 创建新的一页
			WritableSheet sheet = workbook.createSheet("First Sheet", 0);
			Label condition0 = new Label(0, 0, "查询条件");
			sheet.addCell(condition0);
			Label condition1 = new Label(1, 0, "大区");
			sheet.addCell(condition1);
			Label condition2 = new Label(2, 0, "开始时间");
			sheet.addCell(condition2);
			Label condition3 = new Label(3, 0, "结束时间");
			sheet.addCell(condition3);
			Label condition4 = new Label(4, 0, "分拣场地名称");
			sheet.addCell(condition4);
			
			Label condition00 = new Label(0, 1, "#");
			sheet.addCell(condition00);
			Label condition10 = new Label(1, 1, conditions[1]);
			sheet.addCell(condition10);
			Label condition20 = new Label(2, 1, conditions[3].substring(0, conditions[3].lastIndexOf("-"))+" "+conditions[3].substring(conditions[3].lastIndexOf("-")+1)+"时");
			sheet.addCell(condition20);
			Label condition30 = new Label(3, 1, conditions[5].substring(0, conditions[5].lastIndexOf("-"))+" "+conditions[5].substring(conditions[5].lastIndexOf("-")+1)+"时");
			sheet.addCell(condition30);
			Label condition40 = new Label(4, 1, conditions[7]);
			sheet.addCell(condition40);
			
			Label title1 = new Label(0, 3, "大区");
			sheet.addCell(title1);
			Label title2 = new Label(1, 3, "分拣场地名称");
			sheet.addCell(title2);
			Label title3 = new Label(2, 3, "操作总单量");
			sheet.addCell(title3);
			Label title4 = new Label(3, 3, "出勤总人数");
			sheet.addCell(title4);
			Label title5 = new Label(4, 3, "平均人效");
			sheet.addCell(title5);
			Label title6 = new Label(5, 3, "正式工数量");
			sheet.addCell(title6);
			Label title7 = new Label(6, 3, "非正式工数量");
			sheet.addCell(title7);
			Label title8 = new Label(7, 3, "其他人员数量");
			sheet.addCell(title8);
			Label title9 = new Label(8, 3, "正式工占比");
			sheet.addCell(title9);
			int i=4;//标志位
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