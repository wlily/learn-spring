package com.baobaotao.conf;

import com.baobaotao.conf.bean.LogDao;
import com.baobaotao.conf.bean.LogonService;
import com.baobaotao.conf.bean.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
//@Import(DaoConfig.class)
public class ServiceConfig {
	
    @Autowired
	private DaoConfig daoConfig;
    
	@Bean
	public LogonService logonService(){
		LogonService logonService = new LogonService();
		System.out.println(daoConfig.logDao() == daoConfig.logDao());
		logonService.setLogDao(daoConfig.logDao());
		logonService.setUserDao(daoConfig.userDao());
		return logonService;
	}

	@Bean(name="logonService25")
	@Autowired
	public LogonService logonService2(UserDao userDao,LogDao logDao){
		LogonService logonService = new LogonService();
		logonService.setUserDao(userDao);
		logonService.setLogDao(logDao);
		return logonService;
	}
}
