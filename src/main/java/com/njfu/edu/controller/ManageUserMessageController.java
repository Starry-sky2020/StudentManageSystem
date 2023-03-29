package com.njfu.edu.controller;

import com.njfu.edu.pojo.Manager;
import com.njfu.edu.pojo.SubmitResult;
import com.njfu.edu.pojo.User;
import com.njfu.edu.service.impl.ManageUserServiceImpl;

import java.io.IOException;
import java.util.List;

public class ManageUserMessageController {

    private ManageUserServiceImpl manageUserMessageService = new ManageUserServiceImpl();

    public List<User> selectAllUser() throws IOException {
        return manageUserMessageService.selectAllUser();
    }

    public void deleteUserById(String id) throws IOException {
        manageUserMessageService.deleteUserById(id);
    }

    public SubmitResult createManger(Manager manager) throws IOException {
        return manageUserMessageService.createManger(manager);
    }
}
