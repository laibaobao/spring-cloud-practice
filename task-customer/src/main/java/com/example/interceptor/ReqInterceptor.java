package com.example.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ReqInterceptor implements HandlerInterceptor {

    private Logger logger = LoggerFactory.getLogger(ReqInterceptor.class);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("Interceptor preHandler method is running !");

        HttpSession session = request.getSession();

        logger.info("session id is " + session.getId());

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        logger.info("Interceptor postHandler method is running !");

        HttpSession session = request.getSession();

        logger.info("session id is " + session.getId());

    }
}
