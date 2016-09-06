package com.jd.pims.comm.aop.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.google.gson.JsonObject;
import com.jd.pims.comm.LoginInfo;

@Component
@Aspect
public class AppLoginVerifyService {
	private static final Logger logger = Logger
			.getLogger(AppLoginVerifyService.class.getName());

	@Around("execution(* com.jd.pims.pem.controller.*.* (..))")
	public Object process(ProceedingJoinPoint joinPoint) throws Throwable {

		logger.debug("拦截方法,判断是否登录!");
		
		Object[] args = joinPoint.getArgs();
		HttpServletRequest request = null;
		for (Object param : args) {
			if (param instanceof HttpServletRequest) {
				request = (HttpServletRequest) param;
				break;
			}
			
		}
		if (request != null ) {
			String empId = request.getParameter("empId");
			if (empId == null || !LoginInfo.isLogin(empId)) {
				String tmp = joinPoint.getSignature().toString();
				logger.debug("您没有得到授权！" + tmp);
				JsonObject retMsg = new JsonObject();
				retMsg.addProperty("returnCode", -1);
				retMsg.addProperty("message", "您没有得到授权！");
				return retMsg.toString();
			}
		}

		Object object = joinPoint.proceed();
		return object;
	}

}
