package com.jd.pims.pem.controller;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.google.gson.Gson;
import com.jd.pims.comm.BaseController;
import com.jd.pims.pem.service.IBizService;

@Controller
@RequestMapping("/export")
public class DataExportController extends BaseController {

	@Autowired
	private IBizService pemService;
	
	/**
	 * 取在岗人数业务接口
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryyydata", method = RequestMethod.POST)
	@ResponseBody
	public String queryYydata(HttpServletRequest request,HttpServletResponse response) {
		String inputs = request.getParameter("inputs");
		int pages=Integer.parseInt(request.getParameter("pages"));
		List<Map<String, Object>> list = pemService.queryYydata(inputs.replace(",", ", ").split(","),pages,8);
		Gson gson = new Gson();
		return gson.toJson(list);
	}
	
	/**
	 * 取在岗人数业务接口
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/yydata", method = RequestMethod.POST)
	@ResponseBody
	public String yydata(HttpServletRequest request,HttpServletResponse response) {
		String inputs = request.getParameter("inputs");
		String url = pemService.yydata(inputs.replace(",", ", ").split(","),request);
		
		return url;
	}
	
}
