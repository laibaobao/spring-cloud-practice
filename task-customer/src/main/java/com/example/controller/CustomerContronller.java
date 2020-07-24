package com.example.controller;


import com.example.bean.User;
import com.example.services.SimpleService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.catalina.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
public class CustomerContronller {


    @Resource
    private SimpleService simpleService;

    private Logger logger = LoggerFactory.getLogger(CustomerContronller.class);


    /*
    @PostMapping("/login")
    public String login(@RequestHeader("Cookie") String cookie, User user, HttpSession session) throws JsonProcessingException {

        logger.info("session id :" + session.getId());
        if(user!=null)
        logger.info("Ribbon : the user is "+ user.toString());
        ObjectMapper mapper = new ObjectMapper();
        String userJson = mapper.writeValueAsString(user);
        logger.info("Ribbon : the userJson is "+ userJson);

        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Cookie",cookie);
        requestHeaders.add("Content-Type","application/json;charset=utf-8");

//        MultiValueMap<String, User> requestBody  = new LinkedMultiValueMap<>();
//        requestBody.add("user",user);
        HttpEntity<String> requestEntity = new HttpEntity<>(user.toString(), requestHeaders);

        String res =  restTemplate.postForObject("http://task-api/login",requestEntity,String.class);
        return res;
    }
     */

    @PostMapping("/login")
    public String login(@RequestHeader("Cookie")String cookie,User user){
        logger.info("Cookie is "+cookie);
        return  simpleService.login(cookie,user);
    }
    @PostMapping("/test")
    public String test(HttpServletRequest request){
        HttpSession session = request.getSession();
        logger.info("session id is "+session.getId());
        session.setAttribute("ss","ss");
        return "get new session";
    }

}
