package com.baobaotao.conf;

import com.baobaotao.conf.bean.LogonService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class JavaConfigTest {
	public static void main(String[] args) {
		
//1.通过构造函数加载配置类		
//         ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConf.class);

//2.通过编码方式注册配置类
//		 AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
//		 ctx.register(DaoConfig.class);
//		 ctx.register(ServiceConfig.class);
//		 ctx.refresh();


//3.通过XML组装@Configuration配置类所提供的配置信息
//		 ApplicationContext ctx = new ClassPathXmlApplicationContext("com/baobaotao/conf/beans2.xml");

//4.通过@Configuration组装XML配置所提供的配置信息
//		 ApplicationContext ctx = new AnnotationConfigApplicationContext(LogonAppConfig.class);

		//5.@Configuration的配置类相互引用
		 ApplicationContext ctx = new AnnotationConfigApplicationContext(DaoConfig.class,ServiceConfig.class);
//         LogonService logonService = ctx.getBean(LogonService.class);
        ctx.getBean("logonService");
        ctx.getBean("userDao");
        ctx.getBean("logDao");


	}
}
