package com.baobaotao.conf.bean;

public class LogDao {
    public LogDao(){
        System.out.println("LogDao construct");
    }

    public void init(){
        System.out.println("LogDao init");
    }

    public void destroy(){
        System.out.println("LogDao destroy");
    }
}
