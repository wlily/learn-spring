package com.baobaotao.event;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestMailSender{
	public ApplicationContext ctx = null;

	private static String[] CONFIG_FILES = {"event/beans.xml"};

	@BeforeClass
	public void setUp() throws Exception {
		ctx = new ClassPathXmlApplicationContext(CONFIG_FILES);
	}

	@Test
	public void testMailSender() {
       MailSender mailSender = (MailSender)ctx.getBean("mailSender");
       mailSender.sendMail("aaa@bbb.com");
	}
}
