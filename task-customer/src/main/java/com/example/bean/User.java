package com.example.bean;


import lombok.Data;


import java.io.Serializable;

@Data
public class User implements Serializable {

    private Integer id;
    private String name;
    private String tel;
    private Integer roleId;

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name=" + name +
                ", tel=" + tel +
                ", roleId='" + roleId + '\'' +
                '}';
    }
}
