package com.baobaotao.conf;

import com.baobaotao.conf.bean.LogDao;
import com.baobaotao.conf.bean.LogonService;
import com.baobaotao.conf.bean.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

@Configuration
//@Import(DaoConfig.class)
@ImportResource("classpath:com/baobaotao/conf/beans3.xml")
public class LogonAppConfig {
    
	@Bean
	@Autowired
	public LogonService logonService(UserDao userDao,LogDao logDao){
		LogonService logonService = new LogonService();
		logonService.setUserDao(userDao);
		logonService.setLogDao(logDao);
		return logonService;		
	}
}
