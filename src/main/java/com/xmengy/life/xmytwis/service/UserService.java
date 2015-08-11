package com.xmengy.life.xmytwis.service;

import com.xmengy.life.xmytwis.common.Result;
import com.xmengy.life.xmytwis.entity.User;

public interface UserService {
	
	public Result<User> queryUserByUserId(String userId);
	
	/**
	 * 检查用户名密码是否匹配 如果匹配 则返回 UserId
	 * @param userName
	 * @param password
	 * @return
	 */
	public Result<String> checkUserNameAndPassword(String userName,String password);
}
