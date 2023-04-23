package com.njfu.edu.pojo;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
public class OperationLog implements Serializable {

    private long id;
    private String operationMsg;
    private int deleteFlag;
    private Long userId;
    private Long managerId;
    private String info;
    private Timestamp updateTime;
    private String indetity;
}
