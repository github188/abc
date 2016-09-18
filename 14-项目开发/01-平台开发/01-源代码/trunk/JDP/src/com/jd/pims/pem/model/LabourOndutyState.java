package com.jd.pims.pem.model;

import com.jd.pims.comm.BaseDataModel;

/**
 * 在岗人数分类统计领域对象
 *
 */
public class LabourOndutyState extends BaseDataModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8549857464663702188L;
	
	private String cuId;
	//区域名称
	private String cuName;
	//员工数量
	private Integer numEmp=0;
	//临时工数量
	private Integer numTemp=0;
	//其他员工数量
	private Integer numOther=0;

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

	

}
