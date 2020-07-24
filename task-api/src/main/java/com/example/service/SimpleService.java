package com.example.service;

import com.example.bean.User;


public interface SimpleService {
    User getUser(int id);
    User saveUser(User user);
    String deleteUser(int id);

}
