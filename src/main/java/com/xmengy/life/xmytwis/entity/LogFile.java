package com.xmengy.life.xmytwis.entity;

public class LogFile {
	private String name;
	private String modifiedTime;
	/**
	 * 文件类型  1：目录   2:sql 耗时     3：业务异常日志  4:异常分析
	 */
	private String fileType;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getModifiedTime() {
		return modifiedTime;
	}
	public void setModifiedTime(String modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	
}
