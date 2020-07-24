package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.bean.RoleAuth;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RoleAuthMapper extends BaseMapper<RoleAuth> {

    @Select("select auth_id from role_auth where role_id = #{roleId}")
    public List<Integer> queryAuth(int roleId);
}
