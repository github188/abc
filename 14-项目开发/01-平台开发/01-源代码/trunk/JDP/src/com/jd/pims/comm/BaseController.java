package com.jd.pims.comm;

import com.google.gson.JsonObject;
import com.google.gson.JsonArray;

/**
 * 	Controller抽象类，所以controller类都扩展此类	
 *
 */
public abstract class BaseController {
	
	protected static int SUCESS_RETURN_COCE=0;
	protected static String SUCESS_RETURN_MESSAGE="请求成功!";
	
	/**
	 * 构造调用成功的返回头信息,{"returnCode":0,"message":"请求成功!"}
	 * @return
	 */
	private JsonObject buildHeader(){
		return this.buildHeader(SUCESS_RETURN_COCE, SUCESS_RETURN_MESSAGE);
	}
	/**
	 * 构造返回的json头信息,如{"returnCode":1,"message":"xxxx"}
	 * @param code 返回码，整型
	 * @param message 返回信息
	 * @return
	 */
	private JsonObject buildHeader(int code,String message){
		JsonObject retMsg=new JsonObject();
		retMsg.addProperty("returnCode", code);
		retMsg.addProperty("message", message);
		return retMsg;
	}
	
	/**
	 * 构造调用成功的返回信息json字符串，包括头信息及返回对象
	 * @param dataModel
	 * @return
	 */
	public String buildSuccessResponse(
			BaseDataModel dataModel) {
		JsonObject retMsg=buildHeader();
		JsonObject result=dataModel.toJsonObject();
		retMsg.add("result", result);
		return retMsg.toString();
	}
	/**
	 * 构造调用成功的返回信息json字符串，包括头信息及返回对象
	 * @param dataModel
	 * @param message
	 * @return
	 */
	public String buildSuccessResponse(
			BaseDataModel dataModel,String message) {
		
		JsonObject retMsg=buildHeader(SUCESS_RETURN_COCE,message);
		JsonObject result=dataModel.toJsonObject();
		retMsg.add("result", result);
		return retMsg.toString();
	}

	/**
	 * 构造调用成功的返回信息json字符串，包括头信息及返回对象数组
	 * @param dataModels
	 * @return
	 */
	public String buildSuccessResponse(
			BaseDataModel[] dataModels) {

		JsonObject retMsg=buildHeader();
		JsonArray result=new JsonArray();
		for(BaseDataModel model: dataModels){
			JsonObject json=model.toJsonObject();
			result.add(json);
		}
		
		retMsg.add("result", result);
		return retMsg.toString();
	}
	/**
	 * 构造调用成功的返回信息json字符串，包括头信息及返回对象数组
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
		return retMsg.toString();
	}
	
	/**
	 * 返回错误的信息
	 * @param code
	 * @param message
	 * @return
	 */
	public String buildFailResponse(int code,String message){
		return buildHeader(code,message).toString();
	}
			
}
