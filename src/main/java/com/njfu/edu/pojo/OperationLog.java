package com.njfu.edu.pojo;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class OperationLog {

    private long id;
    private String operationMsg;
    private int deleteFlag;
    private long userId;
    private String info;
    private Timestamp updateTime;
}
