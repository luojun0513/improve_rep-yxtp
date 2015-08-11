package com.xmengy.life.xmytwis.entity;

import java.util.Date;

public class CleanLog {
	private String id;
	private Integer version;
	private String txnId;
	private String status;
	private String updateTime;
	private String remark;
	private String operId;
	private String tunnelId;
	private String batchIdOut;
	private String orderId;
	private String txnType;
	private String pmtTp;
	private String merchId;
	private String respCode;
	private String resMsg;
	private String errorDesp;
	private String chkSts;

	/**
	 * 三方报文头-响应码
	 */
	private String thirdHeadCode;

	/**
	 * 三方报文头-响应信息
	 */
	private String thirdHeadMsg;

	/**
	 * 三方响应明细-响应码
	 */
	private String thirdBodyCode;

	/**
	 * 三方响应明细-响应信息
	 */
	private String thirdBodyMsg;

	/**
	 * 通道商户号
	 */
	private String exMerchId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getTxnId() {
		return txnId;
	}

	public void setTxnId(String txnId) {
		this.txnId = txnId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getOperId() {
		return operId;
	}

	public void setOperId(String operId) {
		this.operId = operId;
	}

	public String getTunnelId() {
		return tunnelId;
	}

	public void setTunnelId(String tunnelId) {
		this.tunnelId = tunnelId;
	}

	public String getBatchIdOut() {
		return batchIdOut;
	}

	public void setBatchIdOut(String batchIdOut) {
		this.batchIdOut = batchIdOut;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getTxnType() {
		return txnType;
	}

	public void setTxnType(String txnType) {
		this.txnType = txnType;
	}

	public String getPmtTp() {
		return pmtTp;
	}

	public void setPmtTp(String pmtTp) {
		this.pmtTp = pmtTp;
	}

	public String getMerchId() {
		return merchId;
	}

	public void setMerchId(String merchId) {
		this.merchId = merchId;
	}

	public String getRespCode() {
		return respCode;
	}

	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}

	public String getResMsg() {
		return resMsg;
	}

	public void setResMsg(String resMsg) {
		this.resMsg = resMsg;
	}

	public String getErrorDesp() {
		return errorDesp;
	}

	public void setErrorDesp(String errorDesp) {
		this.errorDesp = errorDesp;
	}

	public String getChkSts() {
		return chkSts;
	}

	public void setChkSts(String chkSts) {
		this.chkSts = chkSts;
	}

	public String getThirdHeadCode() {
		return thirdHeadCode;
	}

	public void setThirdHeadCode(String thirdHeadCode) {
		this.thirdHeadCode = thirdHeadCode;
	}

	public String getThirdHeadMsg() {
		return thirdHeadMsg;
	}

	public void setThirdHeadMsg(String thirdHeadMsg) {
		this.thirdHeadMsg = thirdHeadMsg;
	}

	public String getThirdBodyCode() {
		return thirdBodyCode;
	}

	public void setThirdBodyCode(String thirdBodyCode) {
		this.thirdBodyCode = thirdBodyCode;
	}

	public String getThirdBodyMsg() {
		return thirdBodyMsg;
	}

	public void setThirdBodyMsg(String thirdBodyMsg) {
		this.thirdBodyMsg = thirdBodyMsg;
	}

	public String getExMerchId() {
		return exMerchId;
	}

	public void setExMerchId(String exMerchId) {
		this.exMerchId = exMerchId;
	}

}
