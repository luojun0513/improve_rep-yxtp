package com.xmengy.life.xmytwis.dao.redis;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import redis.clients.jedis.Jedis;

import com.xmengy.life.xmytwis.entity.User;

@Component
public class UserRedis extends BaseRedisDAO {
	
	@Value("${db.conn.driver}")
	private String dbDriver;
	
	public User queryUserById(String userId){
		Jedis jedis = getCollection();
		String key = "user:" + userId;
		Map<String,String> userMap = jedis.hgetAll(key);
		if(userMap == null || userMap.size() == 0){
			return null;
		}
		User user = new User();
		user.setUserId(userId);
		user.setUserName(userMap.get("username"));
		user.setPassword(userMap.get("password"));
		user.setAuth(userMap.get("auth"));
		return user;
	}
	
	public String queryUserIdByUserName(String userName){
		Jedis jedis = getCollection();
		String userId = jedis.hget("users", userName);
		if(StringUtils.hasText(userId)){
			return null;
		}
		return userId;
	}
	
	public String queryUserPasswordById(String userid){
		
		System.out.println("dbDriver : " + dbDriver);
		
		Jedis jedis = getCollection();
		String key = "user:" + userid;
		String password = jedis.hget(key, "password");
		return password;
	} 
}
