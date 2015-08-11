package com.creditease.oltp.service;

import java.io.File;

public interface ReadDataGramService {
	public File[] listFiles(String path);

	public String datagramView(String path);
}
