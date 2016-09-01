package com.jd.pims.comm;

import com.google.gson.JsonObject;
import com.google.gson.JsonArray;

/**
 * 	Controller抽象类，所以controller类都扩展此类	
 *
 */
public abstract class BaseController {
	
	private static int SUCESS_RETURN_COCE=0;
	private static String SUCESS_RETURN_MESSAGE="请求成功!";
	
	private JsonObject buildHeader(){
		return this.buildHeader(SUCESS_RETURN_COCE, SUCESS_RETURN_MESSAGE);
	}
	private JsonObject buildHeader(int code,String message){
		JsonObject retMsg=new JsonObject();
		retMsg.addProperty("returnCode", code);
		retMsg.addProperty("message", message);
		return retMsg;
	}
	
	/**
	 * 
	 * @param dataModel
	 * @return
	 */
	public String buildSuccessResponse(
			BaseDataModel dataModel) {
		JsonObject retMsg=buildHeader();
		JsonObject result=dataModel.toJsonObject();
		retMsg.add("result", result);
		return retMsg.getAsString();
	}
	/**
	 * 构建返回json字符串
	
	 * @param dataModel
	 * @param message
	 * @return
	 */
	public String buildSuccessResponse(
			BaseDataModel dataModel,String message) {
		
		JsonObject retMsg=buildHeader(SUCESS_RETURN_COCE,message);
		JsonObject result=dataModel.toJsonObject();
		retMsg.add("result", result);
		return retMsg.getAsString();
	}

	public String buildSuccessResponse(
			BaseDataModel[] dataModels) {

		JsonObject retMsg=buildHeader();
		JsonArray result=new JsonArray();
		for(BaseDataModel model: dataModels){
			JsonObject json=model.toJsonObject();
			result.add(json);
		}
		
		retMsg.add("result", result);
		return retMsg.getAsString();
	}
	/**
	 * 构建返回json字符串
	 * @param dataModels
	 * @param message
	 * @return
	 */
	public String buildSuccessResponse(
			BaseDataModel[] dataModels,String message) {
		JsonObject retMsg=buildHeader(SUCESS_RETURN_COCE,message);
		JsonArray result=new JsonArray();
		for(BaseDataModel model: dataModels){
			JsonObject json=model.toJsonObject();
			result.add(json);
		}
		
		retMsg.add("result", result);
		return retMsg.getAsString();
	}
	
	public String buildFailResponse(int code,String message){
		return buildHeader(code,message).getAsString();
	}
			
}
