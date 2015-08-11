package com.xmengy.life.xmytwis.common;
import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 只能测试非页面类 mapper,service
 * @author luxinbi
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/spring-*.xml")
public class BaseTest{
	private long start;
	@Before
	public void setUp() {
		start = System.currentTimeMillis();
	}
	
	@After
	public void tearDown() {
		System.out.println("耗时：" + (System.currentTimeMillis() - start));
	}
}
