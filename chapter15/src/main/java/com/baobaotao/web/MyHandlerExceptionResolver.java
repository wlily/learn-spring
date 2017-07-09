package com.baobaotao.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MyHandlerExceptionResolver {

    @ExceptionHandler(RuntimeException.class)
    public String handleException(RuntimeException re, HttpServletRequest request) {
        return "forward:error.jsp";
    }
}
