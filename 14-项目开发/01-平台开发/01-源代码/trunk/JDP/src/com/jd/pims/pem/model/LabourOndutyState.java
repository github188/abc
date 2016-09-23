package com.jd.pims.pem.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.jd.pims.comm.BaseDataModel;

/**
 * 在岗人数分类统计领域对象
 *
 */
public class LabourOndutyState extends BaseDataModel implements Comparable<LabourOndutyState> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8549857464663702188L;
	
	private String cuId;
	
	private String dayTime;
	//区域名称
	private String cuName;
	//员工数量
	private Integer numEmp=0;
	//临时工数量
	private Integer numTemp=0;
	//其他员工数量
	private Integer numOther=0;
	
	private SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd");

	public String getCuId() {
		return cuId;
	}

	public void setCuId(String cuId) {
		this.cuId = cuId;
	}

	public String getCuName() {
		return cuName;
	}

	public void setCuName(String cuName) {
		this.cuName = cuName;
	}
	
	public Integer getNumEmp() {
		return numEmp;
	}

	public void setNumEmp(Integer numEmp) {
		this.numEmp = numEmp;
	}

	public Integer getNumTemp() {
		return numTemp;
	}

	public void setNumTemp(Integer numTemp) {
		this.numTemp = numTemp;
	}

	public Integer getNumOther() {
		return numOther;
	}

	public void setNumOther(Integer numOther) {
		this.numOther = numOther;
	}

	public String getDayTime() {
		return dayTime;
	}

	public void setDayTime(String dayTime) {
		this.dayTime = dayTime;
	}

	@Override
	public int compareTo(LabourOndutyState o) {
		// TODO Auto-generated method stub
		try {
			Date otherTime = sFormat.parse(o.getDayTime());
			Date thisTime=sFormat.parse(this.getDayTime());
			if(thisTime.getTime()>otherTime.getTime()){
				return 1;
			}else if(thisTime.getTime()<otherTime.getTime()){
				return -1;
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}

	

}
