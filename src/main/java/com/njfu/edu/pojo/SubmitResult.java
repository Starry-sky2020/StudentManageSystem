package com.njfu.edu.pojo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class SubmitResult {

    //用户错误编码
    private int Code;
    //用户注册结果
    private Boolean result;
    //用户注册结果信息
    private String message;

    //用户名不合法
    public static final int ERROR_CODE_1 = 1;
    //密码不合法
    public static final int ERROR_CODE_2 = 2;
    //用户已经存在
    public static final int ERROR_CODE_3 = 3;
    //用户注册成功
    public static final int ERROR_CODE_4 = 4;

}
