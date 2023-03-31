package com.njfu.edu.View;

import com.njfu.edu.pojo.Manager;

import java.io.IOException;
import java.text.ParseException;
import java.util.Map;

public interface SystemView {

    /**
     * 主界面
     * @return
     */
    Integer MainUI();

    /**
     * 登录界面
     * 进行身份选择
     * @return
     */
    Integer LoginUI();

    /**
     * 用户登录界面
     * 获取用户信息
     * @return
     */
    Map<String,String> UserLogin();

    /**
     * 管理员登录界面
     * 获取管理员信息
     * @return
     */
    Map<String, String> ManagerLogin();

    /**
     * 管理学生系统界面
     * @return
     */
    Integer ManageStudentSystemView();

    /**
     * 管理学生信息
     * @param choice
     * @throws IOException
     */
    void ManageStudentMessage(int choice) throws IOException, ParseException;

    /**
     * 用户注册
     * @return
     */
    Map<String,String> UserSubmit();

    /**
     * 管理员视图界面
     * @return
     */
    Integer ManageUserView();

    /**
     * 管理用户信息界面
     * @return
     */
    Integer ManageUserMessageView();

    /**
     * 管理员特有功能
     * 管理用户信息
     * @param choice
     */
    void ManageUserMessage(Integer choice) throws IOException;

    /**
     * 创建管理员账户
     * @return
     */
    Manager createManager();

}
