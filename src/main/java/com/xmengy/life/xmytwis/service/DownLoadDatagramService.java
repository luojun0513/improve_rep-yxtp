package com.xmengy.life.xmytwis.service;

import java.io.File;
import java.util.List;

import com.xmengy.life.xmytwis.entity.LogFile;

public interface DownLoadDatagramService {
	public List<LogFile> listFiles(String path);

	public String datagramView(String currPath);
}
