package com.baobaotao.editor;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.assertNotNull;

public class TestCustomerEditor{
	public ApplicationContext ctx = null;

	private static String[] CONFIG_FILES = {"editor/beans.xml"};

	@BeforeClass
	public void setUp() throws Exception {
		ctx = new ClassPathXmlApplicationContext(CONFIG_FILES);
	}

	@Test
	public void testCustomerCarEditor(){
		Boss boss = (Boss) ctx.getBean("boss");
		assertNotNull(boss);
	    System.out.println(boss);
	} 
	
}
