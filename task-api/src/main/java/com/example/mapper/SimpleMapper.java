package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.bean.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SimpleMapper extends BaseMapper<User> {


    @Select("select * from user where id = #{id}")
    User queryUser(int id);


    @Select("select * from user")
    List<User> queryUsers();

    @Insert("insert into user values(#{id},#{name},#{tel},#{roleId})")
    int insertUser(User user);


    @Delete("delete from user where id = #{id}")
    boolean deleteUser(int id);
}
