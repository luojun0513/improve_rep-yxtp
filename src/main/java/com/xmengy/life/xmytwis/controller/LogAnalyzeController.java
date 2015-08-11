package com.xmengy.life.xmytwis.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONArray;
import com.xmengy.life.xmytwis.constant.LogAnalyzeConstant;
import com.xmengy.life.xmytwis.entity.CleanLog;
import com.xmengy.life.xmytwis.entity.ExceptionDetail;
import com.xmengy.life.xmytwis.entity.LogFile;
import com.xmengy.life.xmytwis.entity.SQLDealDetail;
import com.xmengy.life.xmytwis.service.LogService;

@Controller
@RequestMapping("/logAnalyze")
public class LogAnalyzeController extends BaseController{
	private static final Logger logger = LoggerFactory.getLogger(LogService.class);
	@Resource
	private LogService logServicce;
	
	@Value("${logFile.path}")
	private String logFilePath;
	
	@RequestMapping(value="/sql/logFileList")
	public String logFileView(String path ,String currPath, Model model){
		if(currPath == null || currPath.length() == 0){
			currPath = logFilePath;
		}else{
			currPath = currPath + File.separator + path;
		}
		
		List<LogFile> logFiles = logServicce.getAllFiles(currPath);
		model.addAttribute("logFiles", logFiles);
		model.addAttribute("currPath", currPath);
		return "/log/sql/logFile_list.html";
	}
	
	/**
	 * @param path
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/sql/analyzeSqlUseTime")
	public String analyzeSqlUseTime(String path,String currPath,Model model){
		String filePath = currPath + File.separator + path;
		List<SQLDealDetail> sqlDealDetails = logServicce.analyzeIbatisSqlUseTime(filePath);
		model.addAttribute("sqlDealDetails", sqlDealDetails);
		model.addAttribute("currPath", filePath);
		return "/log/sql/sql_userTime_view.html";
	}
	
	@RequestMapping(value="/exception/analyzeException")
	public String analyzeException(String path,String currPath,Model model){
		logger.info("LogAnalyzeController analyzeException param path : " + path + " currPath : " + currPath);
		String filePath = currPath + File.separator + path;
		List<ExceptionDetail> exceptionDetails = logServicce.analyzeException(filePath);
		model.addAttribute("exceptionDetails", exceptionDetails);
		model.addAttribute("currPath", filePath);
		return "/log/exception/exception_view.html";
	}
	
	@RequestMapping(value="/exception/exceptionViewDetail")
	public String exceptionViewDetail(String currPath,String exceptionName,Model model){
		logger.info("LogAnalyzeController exceptionViewDetail param currPath : " + currPath + " exceptionName : " + exceptionName);
		List<String> exceptionDetails = logServicce.getLinesContainsKey(currPath,exceptionName);
		model.addAttribute("exceptionDetails", exceptionDetails);
		model.addAttribute("currPath", currPath);
		return "/log/exception/exception_view_detail.html";
	}
	
	@RequestMapping(value="/exception/toPrintOrderLog")
	public String toPrintOrderLog(String currPath,String fileName,Model model ){
		logger.info("LogAnalyzeController printOrderLog param currPath : " + currPath + " fileName:" + fileName);
		currPath = currPath + File.separator + fileName;
		model.addAttribute("currPath", currPath);
		return "/log/exception/printOrderLog.html";
	}
	
	@RequestMapping(value="/exception/printOrderLog")
	public void printOrderLog(String currPath,String key,HttpServletResponse response,Model model ){
		logger.info("LogAnalyzeController printOrderLog param currPath : " + currPath);
		List<String> exceptionDetails = logServicce.getLinesContainsKey(currPath,key);
		model.addAttribute("currPath", currPath);
		StringBuffer strBuf = new StringBuffer();
		for(String str : exceptionDetails){
			strBuf.append("<p>"+str+"</p>");
		}
		logger.info("LogAnalyzeController printOrderLog result strBuf : " + exceptionDetails);
		ajaxSendStr(strBuf.toString(), response);
	}
	
	@RequestMapping(value="/clean/cleanLogIntoDB")
	public void cleanLogIntoDB(HttpServletResponse response){
		logger.info("LogAnalyzeController cleanLogIntoDB");
		logServicce.cleanLogIntoDB();
		ajaxSendStr("简要日志入库完毕",response);
	}
	
	@RequestMapping(value="/clean/queryCleanLog")
	public String queryCleanLog(String txnId,Model model){
		logger.info("LogAnalyzeController queryCleanLog param txnId : " + txnId);
		List<CleanLog> cleanLogs = new ArrayList<CleanLog>();
		if(!StringUtils.isEmpty(txnId)){
			cleanLogs = logServicce.queryCleanLog(txnId);
		}
		model.addAttribute("cleanLogs",cleanLogs);
		model.addAttribute("txnId", txnId);
		return "/log/cleanLog/cleanLogs.html";
	}
}
