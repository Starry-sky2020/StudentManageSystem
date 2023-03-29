package com.njfu.edu.pojo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class ImportResult {

    private Boolean result;
    private int code;
    private String message;
    //存储导入成功的数据
    private List<Student> successData;
    //存储导入失败的数据
    private List<Student> failData;
    //存储导入已经存在的数据
    private List<Student> existData;

    public static final int CODE_1 = 1;
    // 文件不存在
    public static final int CODE_2 = 2;
    // 文件格式不正确
    public static final int CODE_3 = 3;

    public ImportResult() {
        successData = new ArrayList<>();
        failData = new ArrayList<>();
        existData = new ArrayList<>();
    }

    public ImportResult(Boolean result, int code, String message, List<Student> successData, List<Student> failData, List<Student> existData) {
        this.result = result;
        this.code = code;
        this.message = message;
        this.successData = successData;
        this.failData = failData;
        this.existData = existData;
    }

    @Override
    public String toString() {
        return "ImportResult{" +
                "result=" + result +
                ", code=" + code +
                ", message='" + message + '\'' +
                "\n successData=" + successData +
                "\n failData=" + failData +
                "\n existData=" + existData +
                '}';
    }
}
