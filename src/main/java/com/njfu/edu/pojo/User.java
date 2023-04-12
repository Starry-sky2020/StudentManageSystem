package com.njfu.edu.pojo;


import lombok.Data;

import java.sql.Timestamp;

@Data
public class User {

    private String user_id;
    private String username;
    private String password;
    private Integer age;
    private boolean sex;
    private String address;
    private long studentId;
    private int deleteFlag;
    private String info;
    private Timestamp updateTime;
}
