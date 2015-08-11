package com.xmengy.life.xmytwis.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xmengy.life.xmytwis.common.Result;
import com.xmengy.life.xmytwis.entity.User;
import com.xmengy.life.xmytwis.service.UserService;

@Controller
public class IndexController {
	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);
	
	private static final String CURRUSER = "currUser";
	private static final String MESSAGE = "message";
	
	@Resource
	private UserService userService;
	
	@RequestMapping(value="/index",method=RequestMethod.GET)
	public String index(HttpServletRequest request) throws IOException{
		System.out.println("uri:" + request.getRequestURI());
		System.out.println("url:" + request.getRequestURL());
		
		InputStream in = request.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		String str = null;
		while((str = reader.readLine()) != null){
			System.out.println(str);
		}
		
		return "/index.html";
	}
	
	@RequestMapping(value="/toLogin",method=RequestMethod.GET)
	public String toLogin(){
		return "/index.html";
	}
	
	@RequestMapping(value="/doLogin",method=RequestMethod.POST)
	public String login(String userName,String password,HttpServletRequest request,Model model){
		logger.info("userName : " + userName + " password : " + password);
		Result<String> serviceResult = userService.checkUserNameAndPassword(userName, password);
		model.addAttribute(MESSAGE, serviceResult.getMsg());
		if(!serviceResult.isCodeSuccess()){
			logger.info("登陆失败");
			return "/index.html";
		}
		Result<User> queryUserResult = userService.queryUserByUserId(serviceResult.getData());
		if(!queryUserResult.isCodeSuccess()){
			logger.info("登陆失败");
			return "/index.html";
		}
		User user = queryUserResult.getData();
		HttpSession session = request.getSession();
		session.setAttribute(CURRUSER,user);
		return "/index.html";
	}
	
}
