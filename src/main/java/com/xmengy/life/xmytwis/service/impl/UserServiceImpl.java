package com.xmengy.life.xmytwis.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.xmengy.life.xmytwis.common.CommonCode;
import com.xmengy.life.xmytwis.common.Result;
import com.xmengy.life.xmytwis.dao.redis.UserRedis;
import com.xmengy.life.xmytwis.entity.User;
import com.xmengy.life.xmytwis.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Resource
	private UserRedis userRedis;
	
	public Result<User> queryUserByUserId(String userId) {
		User user = userRedis.queryUserById(userId);
		if(user == null){
			return new Result<User>(CommonCode.USER_NOT_EXITS,null);
		}
		return new Result<User>(user);
	}
	
	public Result<String> checkUserNameAndPassword(String userName,String password) {
		String userId = userRedis.queryUserIdByUserName(userName);
		if(StringUtils.hasText(userName)){
			return new Result<String>(CommonCode.USER_NOT_EXITS,null);
		}
		String passwordDb = userRedis.queryUserPasswordById(userId);
		if(!password.equals(passwordDb)){
			return new Result<String>(CommonCode.USER_ERROR_PASSWORD,null);
		}
		return new Result<String>(userId);
	}

}
