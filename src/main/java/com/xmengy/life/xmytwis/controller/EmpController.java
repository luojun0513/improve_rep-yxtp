package com.xmengy.life.xmytwis.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xmengy.life.xmytwis.entity.Emp;
import com.xmengy.life.xmytwis.service.EmpService;

@Controller
@RequestMapping("/emp")
public class EmpController extends BaseController{
	private static final Logger logger = LoggerFactory.getLogger(EmpController.class);
	@Resource
	private EmpService empService;
	
	@RequestMapping(value="/queryAllEmp",method=RequestMethod.GET)
	public String queryAllEmp(Model model){
		List<Emp> empList = empService.selectAllEmp();
		logger.info(ToStringBuilder.reflectionToString(empList));
		model.addAttribute("empList", empList);
		return "/emp/emp_list.html";
	}
	
}
