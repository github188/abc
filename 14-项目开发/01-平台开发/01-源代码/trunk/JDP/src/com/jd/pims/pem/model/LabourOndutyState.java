package com.jd.pims.pem.model;

import com.jd.pims.comm.BaseDataModel;

/**
 * 在岗人数统计领域对象
 *
 */
public class LabourOndutyState extends BaseDataModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8549857464663702188L;
	
	private String cuId;
	
	private Integer numEmp;
	
	private Integer numTemp;
	
	private Integer numOther;

	public String getCuId() {
		return cuId;
	}

	public void setCuId(String cuId) {
		this.cuId = cuId;
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
