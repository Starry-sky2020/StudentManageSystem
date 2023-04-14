package com.njfu.edu.pojo;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class StudentClass {

    private int id;
    private String studentclassName;
    private int deleteFlag;
    private int collegeId;
    private String info;
    private Timestamp updateTime;
}
