package com.xmengy.life.xmytwis.dao;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.xmengy.life.xmytwis.common.BaseParamDTO;
import com.xmengy.life.xmytwis.common.Pagination;


public class SuperDAO extends SqlSessionDaoSupport implements ISuperDAO{

	public Integer insert(String statementName, Object parameterObject) {
		return getSqlSession().insert(statementName, parameterObject);
	}

	public Integer update(String statementName, Object parameterObject) {
		return getSqlSession().update(statementName, parameterObject);
	}

	public Integer delete(String statementName, Object parameterObject) {
		return getSqlSession().delete(statementName, parameterObject);
	}

	public <T> List<T> getList(String statementName, Object parameterObject) {
		return getSqlSession().selectList(statementName, parameterObject);
	}

	public Pagination queryPagination(String statementName,BaseParamDTO baseParamDTO) {
		if (baseParamDTO == null || !baseParamDTO.getPage()) {
            return null;
        }
        // 计算记录起始值和结束值
        Integer totalCount = (Integer) this.getSqlSession().selectOne(statementName + "-count", baseParamDTO);
        int StartRow = (baseParamDTO.getPageNum() - 1) * baseParamDTO.getPageSize();
        int endRow = baseParamDTO.getPageNum()*baseParamDTO.getPageSize();
        List resultList = this.getSqlSession().selectList(statementName, baseParamDTO,new RowBounds(StartRow, endRow));

        return new Pagination(baseParamDTO.getPageSize(), baseParamDTO.getPageNum(), totalCount, resultList);
	}

	public Pagination queryPagination(String statementName, HashMap query,BaseParamDTO baseParamDTO) {
		if (baseParamDTO == null || !baseParamDTO.getPage()) {
            return null;
        }
        // 计算记录起始值和结束值
        Integer totalCount = (Integer) this.getSqlSession().selectOne(statementName + "-count", query);
        List resultList = this.getSqlSession().selectList(statementName, query, new PageBounds(baseParamDTO.getPageNum(), baseParamDTO.getPageSize()));
        return new Pagination(baseParamDTO.getPageSize(), baseParamDTO.getPageNum(), totalCount, resultList);
	}

	@SuppressWarnings("unchecked")
    public <T, V> Map<T, V> getMap(String statementName, Object parameterObject, String key) {
        return this.getSqlSession().selectMap(statementName, parameterObject, key);

    }

    @SuppressWarnings("unchecked")
    public <T> T getObject(String statementName, Object parameterObject) {
        return (T) this.getSqlSession().selectOne(statementName, parameterObject);

    }

	public String getId() {
		Date date = new Date();
		String dateStr = new SimpleDateFormat("yyyyMMddHHmmssS").format(date);
		String id = dateStr + (new Random().nextInt(89999) + 10000);
		return id;
	}
}
