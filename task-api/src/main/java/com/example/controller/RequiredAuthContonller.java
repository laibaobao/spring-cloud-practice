package com.example.controller;

import com.example.bean.User;
import com.example.service.SimpleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RequiredAuthContonller {

    @Autowired
    SimpleService simpleService;

    @GetMapping(value = "/user")
    public String getUser(int id) {

        User user = simpleService.getUser(id);

        return user.toString();
    }

    @PostMapping(value = "/user")
    public String saveUser(User user) {
         simpleService.saveUser(user);
        return user.toString();
    }

    @DeleteMapping("/user")
    public String deleteUser(int id) throws Throwable{

        return simpleService.deleteUser(id);
    }
    @ExceptionHandler(Exception.class)
    public String authException(Exception ex) {

        return ""+ex.getMessage();
    }
}
