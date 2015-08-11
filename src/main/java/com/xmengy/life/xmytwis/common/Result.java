package com.xmengy.life.xmytwis.common;

import java.io.Serializable;
import java.util.List;

public class Result<T> implements Serializable{
	private static final long serialVersionUID = -1360748780920780828L;
	protected Integer code;
	protected String msg;
	protected EnumCode enumCode;
	protected T data;
	protected List<String> errorData;

	@Override
	public String toString() {
		return "Result [code=" + code + ", msg=" + msg + ", enumCode="
				+ enumCode + ", data=" + data + ", errorData=" + errorData
				+ "]";
	}

	public Result(T data) {
		this(CommonCode.SUCCESS_CODE, data);
	}

	public Result(EnumCode enumCode, T data) {
		if (enumCode == null) {
			throw new IllegalArgumentException("EnumCode is null");
		}
		this.enumCode = enumCode;
		this.code = enumCode.getCode();
		this.msg = enumCode.getMsg();
		this.data = data;
	}

	public Result(EnumCode enumCode, T data, List<String> errorData) {
		if (enumCode == null) {
			throw new IllegalArgumentException("EnumCode is null");
		}
		this.enumCode = enumCode;
		this.code = enumCode.getCode();
		this.msg = enumCode.getMsg();
		this.data = data;
		this.errorData = errorData;
	}

	public boolean isCodeSuccess() {
		return CommonCode.SUCCESS_CODE.getCode().equals(this.getCode());
	}

	public String getMsg() {
		return msg;
	}

	public Integer getCode() {
		return code;
	}

	public void addMsg(String msg) {
		if (this.msg == null) {
			this.msg = msg;
		} else {
			this.msg = this.msg + msg;
		}
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public List<String> getErrorData() {
		return errorData;
	}

	public void setErrorData(List<String> errorData) {
		this.errorData = errorData;
	}

	public EnumCode getEnumCode() {
		return enumCode;
	}

	public void setEnumCode(EnumCode enumCode) {
		this.enumCode = enumCode;
	}
}
