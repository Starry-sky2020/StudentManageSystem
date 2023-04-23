package com.njfu.edu.pojo;


import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
public class User implements Serializable {

    private Long user_id;
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
