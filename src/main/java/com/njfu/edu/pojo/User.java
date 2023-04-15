package com.njfu.edu.pojo;


import lombok.Data;

import java.sql.Timestamp;

@Data
public class User {

    private String user_id;
    private String username;
    private String password;
    private Integer age;
    private Integer sex;
    private String address;
    private Long studentId;
    private Integer deleteFlag;
    private String info;
    private Timestamp updateTime;
}
