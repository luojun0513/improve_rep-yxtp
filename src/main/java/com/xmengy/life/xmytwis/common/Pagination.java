package com.xmengy.life.xmytwis.common;


import java.io.Serializable;

import java.util.List;

public class Pagination<P> implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final int DEFAULT_PAGE_SIZE = 10;

    public static final int DEFAULT_PAGE_NUM = 1;

    private int pageSize;

    private int pageNum;

    private int totalCount;

    private int pageCount;

    private List<P> datas = null;

    public Pagination(int pageSize, int pageNum, int totalCount, List<P> datas) {
        this.pageSize = pageSize;
        this.pageNum = pageNum;
        this.totalCount = totalCount;
        this.datas = datas;
        if (this.pageSize == 0) {
            pageCount = 0;
        } else if (this.totalCount % this.pageSize == 0) {
            pageCount = this.totalCount / this.pageSize;
        } else {
            pageCount = totalCount / this.pageSize + 1;
        }
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getPageNum() {
        return pageNum;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public int getPageCount() {
        return this.pageCount;
    }

    public List<P> getDatas() {
        return datas;
    }

}
