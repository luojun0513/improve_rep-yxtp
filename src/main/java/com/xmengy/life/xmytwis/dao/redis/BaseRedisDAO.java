package com.xmengy.life.xmytwis.dao.redis;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;

@Component
public class BaseRedisDAO {
	@Resource
	private SlaveRedisNode slaveRedisNode;
	
	public Jedis getCollection(){
		return slaveRedisNode.createResource();
	}
}
