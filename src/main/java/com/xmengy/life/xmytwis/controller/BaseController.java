package com.xmengy.life.xmytwis.controller;

import java.io.IOException;

import java.io.PrintWriter;
import java.util.Collection;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseController {
	private static final Logger logger = LoggerFactory.getLogger(BaseController.class);
	
	
	public void ajaxSendStr(String str,HttpServletResponse response){
		PrintWriter pw;
		try {
			pw = response.getWriter();
			pw.write(str);
			pw.flush();
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void ajaxSendJSON(Object obj,HttpServletResponse response){
		logger.info("BaseController.ajaxSendJSON #obj" + ToStringBuilder.reflectionToString(obj));
		String jsonStr = JSONObject.fromObject(obj).toString();
		logger.info("BaseController.ajaxSendJSON #obj" + jsonStr);
		ajaxSendStr(jsonStr, response);
	}
	
	public void ajaxSendJSON(Collection obj,HttpServletResponse response){
		logger.info("ajaxSendJSON #Collection:"+obj);
		String jsonStr = JSONArray.fromObject(obj).toString();
		logger.info("ajaxSendJSON #jsonStr:"+jsonStr);
		ajaxSendStr(jsonStr, response);
	}
}
