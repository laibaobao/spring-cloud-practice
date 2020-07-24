package com.example.controller;


import com.example.bean.User;
import com.example.service.SimpleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.session.Session;
import org.springframework.session.data.redis.RedisIndexedSessionRepository;
import org.springframework.session.data.redis.RedisSessionRepository;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Base64;
import java.util.HashMap;

@RestController
public class SimpleController {

    private static HashMap hashMap = new HashMap<>();
    @Autowired
    SimpleService simpleService;

    @Value("${server.port}")
    private String port;

    @Autowired
    RedisIndexedSessionRepository redisSessionRepository;

    private Logger logger = LoggerFactory.getLogger(SimpleController.class);


    private String base64Decode(String base64Value) {
        try {
            byte[] decodedCookieBytes = Base64.getDecoder().decode(base64Value);
            return new String(decodedCookieBytes);
        }
        catch (Exception e) {
            return null;
        }
    }


    @PostMapping("/login")
    public String login(@RequestBody User user, HttpServletRequest request){
        HttpSession session = request.getSession();
        String sessionid = session.getId();
        String token = request.getHeader("SESSION");
        logger.info("session id :" + sessionid);

    //    Session rsession = redisSessionRepository.findById(sessionid);


        if(session.getAttribute(sessionid)==null) {
            User realuser = simpleService.getUser(user.getId());
            if (user.equals(realuser)) {
                session.setAttribute(sessionid, user);

                return "First Login successfully on port : "+port;
            }
            return "Login error on port : " + port;
        }
        else {
            return session.getAttribute(sessionid).toString() + "has been online successfully ";
        }
    }


    @GetMapping("/test")
    public String test(HttpSession session){

        logger.info("session id "+ session.getId());

        return port +"  tests successful";
    }
    @GetMapping(value = "/wrong")
    public int dowrong() {

        return 1000 / 0;
    }

    @ExceptionHandler(Exception.class)
    public String authException(Exception ex) {

        return ""+ex.getMessage();
    }


}
