package com.example.bean;

import java.io.Serializable;


public class User implements Serializable {

    private static final long serialVersionUID = 6242885718722421839L;
    private Integer id;
    private String name;
    private String tel;
    private Integer roleId;

    public User() {
    }

    public User(Integer id, String name, String tel, int roleId) {
        this.id = id;
        this.name = name;
        this.tel = tel;
        this.roleId = roleId;
    }

    public User(User user) {
        this.id = user.id;
        this.name = user.name;
        this.tel = user.tel;
        this.roleId = user.roleId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", tel='" + tel + '\'' +
                ", roleId=" + roleId +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object instanceof User){
            User obj = (User)object;
        return obj.getId() == id && obj.getName() .equals( name )&& obj.getRoleId() == roleId && obj.getTel().equals( tel);}

        return false;
    }
}
