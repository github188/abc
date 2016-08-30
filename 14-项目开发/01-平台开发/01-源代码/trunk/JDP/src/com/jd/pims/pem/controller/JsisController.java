package com.jd.pims.pem.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/as")
public class JsisController {
	@Value("${jsif.address}")  
    private String jsifAddress;

	public String getJsifAddress() {
		return jsifAddress;
	}

	public void setJsifAddress(String jsifAddress) {
		this.jsifAddress = jsifAddress;
	}
}
