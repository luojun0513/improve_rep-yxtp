package com.xmengy.life.xmytwis.service.impl;

import java.io.File;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.creditease.oltp.service.IHello;
import com.creditease.oltp.service.ReadDataGramService;
import com.xmengy.life.xmytwis.entity.LogFile;
import com.xmengy.life.xmytwis.service.DownLoadDatagramService;

@Service
public class DownLoadDatagramServiceImpl implements DownLoadDatagramService{
	
	private ReadDataGramService readDataGramService;
	
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss:S");
	
	public DownLoadDatagramServiceImpl(){
		try {
			readDataGramService = (ReadDataGramService) Naming.lookup("rmi://localhost:8888/ReadDataGramService");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<LogFile> listFiles(String path) {
		List<LogFile> logFiles = new ArrayList<LogFile>();
		File[] files = readDataGramService.listFiles(path);
		for(File fileT : files){
			LogFile fileTemp = new LogFile();
			String fileName = fileT.getName();
			fileTemp.setName(fileName);
			fileTemp.setModifiedTime(sdf.format(new Date(fileT.lastModified())) );
			if(fileT.isFile()){
				fileTemp.setFileType("4");
			}else{
				fileTemp.setFileType("1");
			}
			logFiles.add(fileTemp);
		}
		return logFiles;
	}

	public String datagramView(String currPath) {
		String result = readDataGramService.datagramView(currPath);
		return result;
	}
	
}
