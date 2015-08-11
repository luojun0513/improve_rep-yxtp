package com.xmengy.life.xmytwis.dao;

import javax.annotation.Resource;

import org.junit.Test;
import com.xmengy.life.xmytwis.common.BaseTest;
import com.xmengy.life.xmytwis.dao.redis.UserRedis;
import com.xmengy.life.xmytwis.entity.User;

public class TestUserRedis extends BaseTest{
	
	@Resource
	private UserRedis userRedis;
	
	@Test
	public void queryUserIdByUserName(){
		String userName = "antirz";
		String result = userRedis.queryUserIdByUserName(userName);
		System.out.println("UserId : " + result);
	}
	
	@Test
	public void queryUserPasswordById(){
		String result = userRedis.queryUserPasswordById("2");
		System.out.println("UserPassword : " + result);
		
	}
	
	@Test
	public void queryUserById(){
		User user = userRedis.queryUserById("2");
	}
}
