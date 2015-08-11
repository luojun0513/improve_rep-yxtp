package com.xmengy.life.xmytwis.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xmengy.life.xmytwis.dao.ISuperDAO;
import com.xmengy.life.xmytwis.entity.Emp;
import com.xmengy.life.xmytwis.service.EmpService;

@SuppressWarnings("restriction")
@Service
public class EmpServiceImpl implements EmpService{

	@Resource
	private ISuperDAO superDAO;
	
	public List<Emp> selectAllEmp() {
		return superDAO.getList("empMapper.selectAllEmp", null);
	}

}
