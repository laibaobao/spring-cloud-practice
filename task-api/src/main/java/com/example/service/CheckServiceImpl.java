package com.example.service;

import com.example.mapper.AuthMapper;
import com.example.mapper.RoleAuthMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckServiceImpl implements CheckService {

    @Autowired
    AuthMapper authMapper;

    @Autowired
    RoleAuthMapper roleAuthMapper;

    /**
     * 校验
     * @param id
     * @return
     */
    @Override
    public Integer check(int id) {

        //获取第一个值。权限最大
        List<Integer> res = roleAuthMapper.queryAuth(id);
        int code = res.get(0);
        return code;
    }
}
