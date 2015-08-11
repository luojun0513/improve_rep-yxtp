package com.xmengy.life.xmytwis.dao.redis;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;

@Component
public class SlaveRedisNode implements IResource<Jedis>{
	@Value("${redis.host}")
	private String host;
	
	@Value("${redis.port}")
	private int port;
	
	@Value("${redis.timeout}")
	private int timeout;
	
	private boolean initFlag = false;
	
	public boolean isInit() {
		return this.initFlag;
	}

	public void init() {
		this.initFlag = true;
	}

	public Jedis createResource() {
		Jedis redis = new Jedis(host, port, timeout);
		redis.connect();
		return redis;
	}

	public void close(Jedis resource) {
		
	}

}
