package com.njfu.edu.service.impl;

import com.njfu.edu.dao.impl.UserDaoImpl;
import com.njfu.edu.pojo.SubmitResult;
import com.njfu.edu.pojo.User;
import com.njfu.edu.service.UserSubmitService;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserSubmitServiceImpl implements UserSubmitService {

//    private ReadFile readFile = new ReadFile();
    private UserDaoImpl userDaoImpl = new UserDaoImpl();
    private SubmitResult submitResult = new SubmitResult();
    /**
     * 用户注册服务
     * @param map
     */
    @Override
    public SubmitResult userSubmit(Map<String, String> map) throws IOException {
        List<User> userList = userDaoImpl.selectUserMessage();

        //正则验证用户名是否合法
        String regName = "^([\u4e00-\u9fa5a-zA-Z0-9]{2,12}$|([a-zA-Z]{2,16})$)";
        Pattern pattern = Pattern.compile(regName);
        Matcher matcher = pattern.matcher(map.get("username"));
        if (!matcher.find()){
            submitResult.setResult(false);
            submitResult.setMessage("用户名不合法，请重新输入");
            submitResult.setCode(SubmitResult.ERROR_CODE_1);
            return submitResult;
        }

        //正则验证用户密码是否合法
        String regPassword = "^(?=.*[0-9])|(?=.*[a-z])|(?=.*[A-Z])|(?=.*[@#$%^&+=])|(?=\\S+$).{4,20}$";
        pattern = Pattern.compile(regPassword);
        matcher = pattern.matcher(map.get("password"));
        if (!matcher.find()){
            submitResult.setResult(false);
            submitResult.setMessage("密码设置不合法，请重新输入");
            submitResult.setCode(SubmitResult.ERROR_CODE_2);
            return submitResult;
        }

        //用户名重复检测
        for (int i = 0; i < userList.size(); i++){
            if (map.get("username").equals(userList.get(i).getUsername())){
                submitResult.setResult(false);
                submitResult.setMessage("用户名已存在，请重新输入注册信息");
                submitResult.setCode(SubmitResult.ERROR_CODE_3);
                return submitResult;
            }
        }

        userDaoImpl.insertUser(map.get("username"),map.get("password"));

        submitResult.setResult(true);
        submitResult.setMessage("用户注册成功");
        submitResult.setCode(SubmitResult.ERROR_CODE_4);

        return submitResult;
    }
}
