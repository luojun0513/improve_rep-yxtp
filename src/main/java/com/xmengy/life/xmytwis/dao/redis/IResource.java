package com.xmengy.life.xmytwis.dao.redis;

import redis.clients.jedis.Jedis;

public interface IResource<T> {
	public boolean isInit();

	public void init();

	public Jedis createResource();

	public void close(Jedis resource);
}
