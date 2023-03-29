package com.njfu.edu.pojo;


import lombok.Data;

@Data
public class User {

    private String user_id;
    private String username;
    private String password;
    private Integer age;
    private boolean sex;
    private String address;

    public User(String user_id, String username, String password, Integer age, boolean sex, String address){
        this.user_id = user_id;
        this.username = username;
        this.password = password;
        this.age = age;
        this.sex = sex;
        this.address = address;
    }

    public User(){

    }

}
