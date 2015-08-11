package com.xmengy.life.xmytwis.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.xmengy.life.xmytwis.constant.LogAnalyzeConstant;
import com.xmengy.life.xmytwis.dao.ISuperDAO;
import com.xmengy.life.xmytwis.entity.CleanLog;
import com.xmengy.life.xmytwis.entity.ExceptionDetail;
import com.xmengy.life.xmytwis.entity.LogFile;
import com.xmengy.life.xmytwis.entity.SQLDealDetail;
import com.xmengy.life.xmytwis.service.LogService;

@Service
public class LogServiceImpl implements LogService{

	private static final Logger logger = LoggerFactory.getLogger(LogService.class);
	
	@Resource
	private ISuperDAO superDAO;
	
	@Value("${logFile.path}")
	private String logFilePath;
	
	@Value("${logFile.path.cleanLogPath}")
	private String cleanLogPath;
	
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss:S");
	
	private static Map<String,String> descMap = new HashMap<String, String>();
	static{
		descMap.put("NS_BILL_BIZ_SIMPLE.getTxnStatusQueryList", "单笔状态查询");
		descMap.put("NS_BILL_BIZ_SIMPLE.getCheckStatusQueryList", "单笔状态核对查询");
		descMap.put("QueryCommSqlMap.getTxnStatusForOverTime", "48小时未回盘");
		descMap.put("NS_BILL_BIZ_SIMPLE.getBillBizSimpleCondition", "查询以及打包数据");
		descMap.put("NS_RES_CODE.getResCodeList", "响应码查询");
		descMap.put("NS_QUERY_LOG.ibatorgenerated_updateByPrimaryKey", "更新查询日志表");
	}
	
	public List<LogFile> getAllFiles(String path) {
		logger.info("LogServiceImpl getAllFiles param path : " + path);
		
		List<LogFile> result = new ArrayList<LogFile>();
		File file = new File(path);
		File[] logFiles = file.listFiles();
		for(File fileT : logFiles){
			LogFile fileTemp = new LogFile();
			String fileName = fileT.getName();
			fileTemp.setName(fileName);
			fileTemp.setModifiedTime(sdf.format(new Date(fileT.lastModified())) );
			if(fileT.isFile()){
				if(fileName.startsWith("147") || fileName.startsWith("193")){
					if(fileName.contains("SQL")){
						fileTemp.setFileType("2");
					}else{
						fileTemp.setFileType("3");
					}
				}else{
					fileTemp.setFileType("4");
				}
			}else{
				fileTemp.setFileType("1");
			}
			result.add(fileTemp);
		}
		return result;
	}

	public List<SQLDealDetail> analyzeIbatisSqlUseTime(String filePath) {
		logger.info("LogServiceImpl analyzeIbatisSqlUseTime param filePath : " + filePath);
		
		Map<String,Map<String,Object>> useTimeMap = new HashMap<String,Map<String,Object>>();
		
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
			String str = "";
			while((str = reader.readLine()) != null){
				handle(useTimeMap,str);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		List<SQLDealDetail> result = getResult(useTimeMap);
		return result;
	}

	private static void handle(Map<String,Map<String,Object>> useTimeMap,String str) {
		String[] params = str.split("】");
		
		String useTimeStr = params[0];
		System.out.println("useTimeStr :  " + useTimeStr);
		String useTime = useTimeStr.substring(useTimeStr.indexOf("共耗时-")+4,useTimeStr.lastIndexOf("-"));
		int useTimeInt = Integer.parseInt(useTime);
		
		String idStr = params[2];
		String id = idStr.substring(idStr.indexOf(":")+1);
		
		if(!useTimeMap.containsKey(id)){
			Map<String,Object> mapTemp = new HashMap<String,Object>();
			mapTemp.put(LogAnalyzeConstant.IBATIS_SQL_MAXTIME, useTimeInt);
			mapTemp.put(LogAnalyzeConstant.IBATIS_SQL_MINTIME, useTimeInt);
			mapTemp.put(LogAnalyzeConstant.IBATIS_SQL_COUNT, 1);
			useTimeMap.put(id, mapTemp);
		}else{
			Map<String,Object> mapTemp = useTimeMap.get(id);
			Integer maxTime = (Integer)mapTemp.get(LogAnalyzeConstant.IBATIS_SQL_MAXTIME);
			Integer minTime = (Integer)mapTemp.get(LogAnalyzeConstant.IBATIS_SQL_MINTIME);
			Integer count = (Integer)mapTemp.get(LogAnalyzeConstant.IBATIS_SQL_COUNT);
			if(useTimeInt > maxTime){
				mapTemp.put(LogAnalyzeConstant.IBATIS_SQL_MAXTIME, useTimeInt);
			}
			if(useTimeInt < minTime){
				mapTemp.put(LogAnalyzeConstant.IBATIS_SQL_MINTIME, useTimeInt);
			}
			mapTemp.put(LogAnalyzeConstant.IBATIS_SQL_COUNT, count+1);
		}
	}
	
	private List<SQLDealDetail> getResult(Map<String, Map<String, Object>> useTimeMap) {
		List<SQLDealDetail> sqlDealDetails = new ArrayList<SQLDealDetail>();
		Set<String> ids = useTimeMap.keySet();
		for(String id : ids){
			SQLDealDetail sqlDealDetail = new SQLDealDetail();
			Map<String,Object> useTimeValue = useTimeMap.get(id);
			Integer maxTime = (Integer) useTimeValue.get(LogAnalyzeConstant.IBATIS_SQL_MAXTIME);
			Integer minTime = (Integer) useTimeValue.get(LogAnalyzeConstant.IBATIS_SQL_MINTIME);
			Integer count = (Integer) useTimeValue.get(LogAnalyzeConstant.IBATIS_SQL_COUNT);
			sqlDealDetail.setCount(count);
			sqlDealDetail.setDesc(descMap.get(id));
			sqlDealDetail.setId(id);
			sqlDealDetail.setMaxTime(maxTime);
			sqlDealDetail.setMinTime(minTime);
			sqlDealDetails.add(sqlDealDetail);
		}
		return sqlDealDetails;
	}

	public List<ExceptionDetail> analyzeException(String filePath) {
		logger.info("LogServiceImpl analyzeException param filePath : " + filePath);
		
		Map<String,ExceptionDetail> exceptionMap = new HashMap<String,ExceptionDetail>();
		
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
			String str = "";
			while((str = reader.readLine()) != null){
				handleAnalyzeException(exceptionMap,str);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		List<ExceptionDetail> exceptionDetails = new ArrayList<ExceptionDetail>();
		for(ExceptionDetail exceptionDetail : exceptionMap.values()){
			exceptionDetails.add(exceptionDetail);
		}
		return exceptionDetails;
	}

	private void handleAnalyzeException(Map<String, ExceptionDetail> useTimeMap, String str) {
		if(str.contains("Exception")){
			ExceptionDetail exceptionDetail = new ExceptionDetail();
			String[] exceptionArr = str.split(":");
			String exceptionName = "";
			String desc = "";
			if(exceptionArr.length == 2){
				exceptionName = exceptionArr[1];
				desc = exceptionArr[0];
			}else{
				exceptionName = exceptionArr[2];
				desc = exceptionArr[1];
			}
			if(exceptionName.contains("】")){
				exceptionName = exceptionName.substring(exceptionName.lastIndexOf("】")+1);
			}
			exceptionName = exceptionName.replace("'", "");
			if(useTimeMap.containsKey(exceptionName)){
				exceptionDetail = useTimeMap.get(exceptionName);
				exceptionDetail.setCount(exceptionDetail.getCount() + 1);
			}else{
				exceptionDetail.setName(exceptionName);
				exceptionDetail.setDesc(desc);
				exceptionDetail.setCount(1);
				useTimeMap.put(exceptionName, exceptionDetail);
			}
		}
	}

	public List<String> getLinesContainsKey(String currPath, String key) {
		logger.info("LogServiceImpl getLinesContainsKey param filePath : " + currPath + " key : " + key);
		List<String> exceptions = new ArrayList<String>();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(currPath)));
			String str = "";
			while((str = reader.readLine()) != null){
				str = str.replace("'", "");
				if(str.contains(key)){
					exceptions.add(str);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return exceptions;
	}
	
	public List<LogFile> cleanLogIntoDB(){
		List<LogFile> result = new ArrayList<LogFile>();
		File cleanLogFile = new File(cleanLogPath);
		if(!cleanLogFile.exists()){
			logger.info("简要日志文件目录不存在，请创建，并导入简要文件");
			return result;
		}
		
		File[] logFiles = cleanLogFile.listFiles();
		if(logFiles == null || logFiles.length == 0){
			return result;
		}
		BufferedReader reader = null;
		for(int i = 0 ; i < logFiles.length ; i++){
			try {
				CleanLog cleanLog = null;
				reader = new BufferedReader(new InputStreamReader(new FileInputStream(logFiles[i])));
				String str = "";
				while((str = reader.readLine()) != null){
					cleanLog = createCleanLog(str);
					superDAO.insert("CLEANLOGMAPPER.insertCleanLog", cleanLog);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			LogFile logFile = new LogFile();
			logFile.setName(logFiles[i].getName());
			logFile.setModifiedTime(sdf.format(new Date(logFiles[i].lastModified())) );
			result.add(logFile);
		}
		return result;
	}

	public List<CleanLog> queryCleanLog(String txnId) {
		List<CleanLog> result = new ArrayList<CleanLog>();
		result = superDAO.getList("CLEANLOGMAPPER.selectCleanLogByTxnId", txnId);
		return result;
	}
	
	public CleanLog createCleanLog(String str){
		CleanLog cleanLog = null;
		String[] cleanLogArr = str.split(" ");
		cleanLog = new CleanLog();
		cleanLog.setTxnId(formatLog(cleanLogArr[5]));
		cleanLog.setMerchId(formatLog(cleanLogArr[6]));
		cleanLog.setStatus(formatLog(cleanLogArr[9]));
		cleanLog.setRespCode(formatLog(cleanLogArr[10]));
		cleanLog.setResMsg(formatLog(cleanLogArr[11]));
		cleanLog.setExMerchId(formatLog(cleanLogArr[12]));
		cleanLog.setOrderId(formatLog(cleanLogArr[13]));
		cleanLog.setTunnelId(formatLog(cleanLogArr[14]));
		cleanLog.setPmtTp(formatLog(cleanLogArr[16]));
		cleanLog.setOperId(formatLog(cleanLogArr[22]));
		String updateTime = cleanLogArr[17];
		String updateTime1 = cleanLogArr[18];
		cleanLog.setUpdateTime(formatLog(updateTime) + " " + formatLog(updateTime1));
		cleanLog.setChkSts(formatLog(cleanLogArr[15]));
		return cleanLog;
	}
	
	public String formatLog(String str){
		if(StringUtils.isEmpty(str)){
			return "";
		}
		if(str.startsWith("[")){
			str = str.substring(1);
		}
		if(str.endsWith("]")){
			str = str.substring(0, str.length()-1);
		}
		return str;
	}
	
	public static void main(String[] args) {
		LogServiceImpl logService = new LogServiceImpl();
		logService.cleanLogPath = "D:\\logs\\cleanLog";
		logService.cleanLogIntoDB();
	}
}
