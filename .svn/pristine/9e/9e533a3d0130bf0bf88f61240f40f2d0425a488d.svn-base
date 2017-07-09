package com.baobaotao.conf;

import com.baobaotao.conf.bean.LogDao;
import com.baobaotao.conf.bean.UserDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class DaoConfig {
    
	
	@Bean(name="userDao")
	public UserDao userDao(){
		return new UserDao();
	}
	
	@Scope("prototype")
	@Bean
	public LogDao logDao(){
		return new LogDao();
	}
}
