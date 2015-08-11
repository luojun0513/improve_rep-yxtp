package com.xmengy.life.xmytwis.mq;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.Connection;

public class MqConnectionFactory {
	public static void main(String[] args) {
		CachingConnectionFactory connectionFactory = new CachingConnectionFactory("localhost");
		
		Connection connection = connectionFactory.createConnection();
		
		connectionFactory.setUsername("guest");
		connectionFactory.setPassword("guest");
		
		System.out.println(connection);
	}
}
