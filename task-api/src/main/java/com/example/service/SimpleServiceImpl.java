package com.example.service;

import com.example.bean.User;
import com.example.mapper.SimpleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;


import java.util.concurrent.TimeUnit;

@Service
public class SimpleServiceImpl implements SimpleService {

    @Autowired
    SimpleMapper simpleMapper;


    @Override
    //  @FlushCache(key = "#id",timePoint = 10,timeUnit = TimeUnit.MINUTES)
    public User getUser(int id) {

        User user = simpleMapper.queryUser(id);
        return user;
    }

    @Override
    //  @CreateCache(key = "#user.id",timePoint = 10,timeUnit = TimeUnit.MINUTES)
    public User saveUser(User user) {

        int flag = simpleMapper.insertUser(user);
        if (flag > -1) {
            return user;
        } else {
            return new User();
        }
    }

    @Override
    // @ClearCache(key = "#id")
    public String deleteUser(int id) {
        boolean hasdeleted = simpleMapper.deleteUser(id);
        if (hasdeleted)
            return "successful";
        return "failed";
    }


    @ExceptionHandler
    public String serviceException(Exception ex) {
        return ex.getMessage();
    }
}
