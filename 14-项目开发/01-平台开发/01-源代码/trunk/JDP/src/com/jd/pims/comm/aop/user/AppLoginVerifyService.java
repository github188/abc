package com.jd.pims.comm.aop.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletWebRequest;

import com.google.gson.JsonObject;
import com.jd.pims.comm.LoginInfo;

@Component
@Aspect
public class AppLoginVerifyService {
	private static final Logger logger = Logger
			.getLogger(AppLoginVerifyService.class.getName());

	
	//@Before("@annotation(com.jd.pims.comm.aop.user.Verify)")
	//@Around("execution(* com.jd.pims.pem.controller.*.*(..))")
	//@Around(value="@annotation(com.jd.pims.comm.aop.user.Verify)")
	 @Around("execution(* com.jd.pims.pem.controller.AppController.* (..))") //all  
	public Object  process(ProceedingJoinPoint joinPoint)
			throws Throwable {
		ServletWebRequest servletContainer = (ServletWebRequest) RequestContextHolder
				.getRequestAttributes();

		HttpServletRequest request = servletContainer.getRequest();

		String empId = request.getParameter("empId");
		if (empId == null||!LoginInfo.isLogin(empId)) {
			String tmp = joinPoint.getSignature().toString();
			logger.debug("您没有得到授权！" + tmp);
			JsonObject retMsg = new JsonObject();
			retMsg.addProperty("returnCode", -1);
			retMsg.addProperty("message", "您没有得到授权！");
			HttpServletResponse response = servletContainer.getResponse();
			response.getWriter().print(retMsg.toString());
			return null;
		}
		Object object = joinPoint.proceed();
		return object;
	}

}
