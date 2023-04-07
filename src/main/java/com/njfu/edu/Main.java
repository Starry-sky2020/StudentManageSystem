package com.njfu.edu;

import com.njfu.edu.controller.IntoSystemController;

import java.io.IOException;


public class Main {
    //判端身份
    public static boolean is_manager;
    public static boolean is_user;
    //域 获取登录系统的用户id或者管理员的id
    public static long userId;
    public static long managerId;
    public static void main( String[] args ) throws IOException {

        IntoSystemController intoSystemController = new IntoSystemController();
        intoSystemController.MainView();

    }
}
