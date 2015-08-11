package com.xmengy.life.xmytwis.common;

import java.io.Serializable;

public class BaseParamDTO implements Serializable {
    private static final long serialVersionUID = -3521553457212773566L;
    private String userNo;          //用户编号
    private Integer pageSize = Pagination.DEFAULT_PAGE_SIZE;       //每页条数
    private Integer pageNum = Pagination.DEFAULT_PAGE_NUM;        //当前页码
    private boolean page = true;
    private Integer startIdx = 1;
    private Integer endIdx = 1;
    private String ip;
    
    public Integer getPageSize() {
        return pageSize;
    }
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
    public Integer getPageNum() {
        return pageNum;
    }
    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }
    public boolean getPage() {
        return page;
    }
    public void setPage(boolean page) {
        this.page = page;
    }
    public Integer getStartIdx() {
        return startIdx;
    }
    public void setStartIdx(Integer startIdx) {
        this.startIdx = startIdx;
    }
    public Integer getEndIdx() {
        return endIdx;
    }
    public void setEndIdx(Integer endIdx) {
        this.endIdx = endIdx;
    }
    public String getIp() {
        return ip;
    }
    public void setIp(String ip) {
        this.ip = ip;
    }
    public String getUserNo() {
        return userNo;
    }
    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }
}
