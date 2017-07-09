package com.baobaotao.conf.bean;

public class UserDao {

    public UserDao(){
        System.out.println("UserDao construct");
    }

    public void init(){
        System.out.println("UserDao init");
    }

    public void destroy(){
        System.out.println("UserDao destroy");
    }

}
