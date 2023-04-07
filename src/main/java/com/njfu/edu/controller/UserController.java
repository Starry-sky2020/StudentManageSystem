package com.njfu.edu.controller;

import com.njfu.edu.pojo.Manager;
import com.njfu.edu.pojo.SubmitResult;
import com.njfu.edu.pojo.User;
import com.njfu.edu.service.impl.UserServiceImpl;
import com.njfu.edu.utils.JDBCUtils;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

public class UserController {

    private UserServiceImpl userService = new UserServiceImpl();


    public List<User> selectAllUser() throws IOException {
        return userService.selectAllUser();
    }

    public void deleteUserById(String id) throws IOException {
        userService.deleteUserById(id);
    }

    public SubmitResult userSubmit(Map<String, String> map) throws IOException {
        return userService.userSubmit(map);
    }
}
