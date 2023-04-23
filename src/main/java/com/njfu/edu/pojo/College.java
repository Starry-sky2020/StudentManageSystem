package com.njfu.edu.pojo;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
public class College implements Serializable {

    private int id;
    private String collegeName;
    private int teacherCount;
    private int deleteFlag;
    private String info;
    private Timestamp updateTime;
}
