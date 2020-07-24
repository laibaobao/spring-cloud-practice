package com.example.services;

import com.example.bean.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name="task-api")
public interface SimpleService {

    @PostMapping("/login")
    public String login(@RequestHeader("Cookie") String cookie, User user);



}
