package com.xmengy.life.xmytwis.service;

import java.util.List;






import com.xmengy.life.xmytwis.entity.CleanLog;
import com.xmengy.life.xmytwis.entity.ExceptionDetail;
import com.xmengy.life.xmytwis.entity.LogFile;
import com.xmengy.life.xmytwis.entity.SQLDealDetail;

public interface LogService {
	public List<LogFile> getAllFiles(String path);
	
	public List<SQLDealDetail> analyzeIbatisSqlUseTime(String filePath);

	public List<ExceptionDetail> analyzeException(String filePath);

	public List<String> getLinesContainsKey(String currPath, String key);

	public List<CleanLog> queryCleanLog(String txnId);
	
	public List<LogFile> cleanLogIntoDB();

}
