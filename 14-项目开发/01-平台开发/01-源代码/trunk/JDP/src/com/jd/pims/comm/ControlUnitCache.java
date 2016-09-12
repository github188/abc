package com.jd.pims.comm;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.jd.pims.user.model.ControlUnit;

public class ControlUnitCache {
	private static final Logger logger = Logger
			.getLogger(ControlUnitCache.class.getName());
	private static Map<String,ControlUnit> cus=new HashMap<String,ControlUnit>();
	
	public static void add(ControlUnit cu){
		cus.put(cu.getId(), cu);
	}
	
	public static ControlUnit get(String cuId){
		return cus.get(cuId);
	}
}
