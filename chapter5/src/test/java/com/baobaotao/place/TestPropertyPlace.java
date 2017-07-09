package com.baobaotao.place;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class TestPropertyPlace{
	public ApplicationContext ctx = null;

	private static String[] CONFIG_FILES = {"placeholder/beans.xml"};

	@BeforeClass
	public void setUp() throws Exception {
		ctx = new ClassPathXmlApplicationContext(CONFIG_FILES);
		
	}

	@Test
	public void test(){
		ctx.getBean("dataSource");
	}
}
