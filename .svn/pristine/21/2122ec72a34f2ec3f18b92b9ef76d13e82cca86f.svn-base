package com.baobaotao.conf;

import com.baobaotao.conf.bean.LogDao;
import com.baobaotao.conf.bean.LogonService;
import com.baobaotao.conf.bean.UserDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConf {

	@Bean(initMethod = "init")
	public UserDao userDao(){
	   return new UserDao();	
	}
	
	@Bean
	public LogDao logDao(){
		return new LogDao();
	}
	
	@Bean
	public LogonService logonService(){
		LogonService logonService = new LogonService();
		logonService.setLogDao(logDao());
		logonService.setUserDao(userDao());
		return logonService;
	}
}
