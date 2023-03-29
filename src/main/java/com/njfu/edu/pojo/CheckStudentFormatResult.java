package com.njfu.edu.pojo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CheckStudentFormatResult {

    private Boolean result;
    private int Code;
    private String message;
    private List<String> successData;
    private List<Integer> failCode;
    private List<String> failData;
    private List<String> existData;

    public static final int CODE_1 = 1;
    public static final int CODE_2 = 2;
    public static final int CODE_3 = 3;
    public static final int CODE_4 = 4;

    public CheckStudentFormatResult(){
        successData = new ArrayList<>();
        failCode = new ArrayList<>();
        failData = new ArrayList<>();
        existData = new ArrayList<>();
    }
}
