package com.jd.pims.pem.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class APIRemoteTest {

	private static final Logger logger = Logger.getLogger(APIRemoteTest.class
			.getName());

	private String login_url = "http://112.74.166.167:8080/JDP/user";
	private String as_url = "http://112.74.166.167:8080/JDP/app";
	
	//private String login_url = "http://localhost:7000/JDP/user";
	//private String as_url = "http://localhost:7000/JDP/app";

	@Before
	public void login() throws UnsupportedEncodingException {
		String action = login_url+"/login.do";
		ArrayList<NameValuePair> list = new ArrayList<NameValuePair>();
		list.add(new BasicNameValuePair("account",
				"jd.cn"));
		list.add(new BasicNameValuePair("password",
				"123"));

		HttpEntity en = new UrlEncodedFormEntity(list, "UTF-8");
		execute(en, action);
	}
	
	@Test
	public void testGetEfficiency() throws UnsupportedEncodingException {
		String action = as_url+"/getEfficiency.do";
		ArrayList<NameValuePair> list = new ArrayList<NameValuePair>();
		list.add(new BasicNameValuePair("empId",
				"11111111111111111111111111111112"));
		list.add(new BasicNameValuePair("cuId",
				"12cad8e307844dcc8525c9357f0692f0"));
		HttpEntity en = new UrlEncodedFormEntity(list, "UTF-8");
		execute(en, action);
	}
	

	@Test
	public void testGetEfficiencyHistory() throws UnsupportedEncodingException {
		String action = as_url+"/getEfficiencyHistory.do";
		ArrayList<NameValuePair> list = new ArrayList<NameValuePair>();
		list.add(new BasicNameValuePair("empId",
				"11111111111111111111111111111112"));
		list.add(new BasicNameValuePair("cuId",
				"12cad8e307844dcc8525c9357f0692f0"));
		list.add(new BasicNameValuePair("startDate", "2016-09-06"));
		list.add(new BasicNameValuePair("endDate", "2016-09-13"));
		list.add(new BasicNameValuePair("interval", "D"));

		HttpEntity en = new UrlEncodedFormEntity(list, "UTF-8");
		execute(en, action);
	}
	
	@Test
	public void testGetLabourAndEfficiencyOfGroup() throws UnsupportedEncodingException{
		String action = as_url+"/getLabourAndEfficiencyOfGroup.do";
		ArrayList<NameValuePair> list = new ArrayList<NameValuePair>();
		list.add(new BasicNameValuePair("empId",
				"11111111111111111111111111111112"));

		HttpEntity en = new UrlEncodedFormEntity(list, "UTF-8");
		execute(en, action);
	}

	/**
	 * API执行方法，此方法是一个模板方法，子类无需实现
	 */
	final public void execute(HttpEntity param, String action) {

		// TODO Auto-generated method stub
		BasicCookieStore cookieStore = new BasicCookieStore();
		CloseableHttpClient httpclient = HttpClients.custom()
				.setDefaultCookieStore(cookieStore).build();
		CloseableHttpResponse response = null;

		try {
			// 发起请求
			long startTime = System.currentTimeMillis();
			HttpUriRequest requst = RequestBuilder.post()
					.setUri(new URI(action)).setEntity(param)
					.build();
			response = httpclient.execute(requst);
			long endTime = System.currentTimeMillis();
			System.out.println("本次API调用耗时----->" + (endTime - startTime) / 1000
					+ "." + (endTime - startTime) % 1000 + "秒");
			extractResult(response);
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			try {
				if (response != null) {
					response.close();
				}
				httpclient.close();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
	}

	protected void extractResult(CloseableHttpResponse response)
			throws Exception {
		int statusCode = response.getStatusLine().getStatusCode();
		if (statusCode == HttpStatus.SC_OK) {
			String results = EntityUtils.toString(response.getEntity());
			JsonObject json = new JsonParser().parse(results).getAsJsonObject();
			logger.debug(json);
		} else {
			logger.error("ERROR:执行失败！" + "\tstatusCode:" + statusCode);
		}
	}
}
