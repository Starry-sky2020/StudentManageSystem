package com.njfu.edu.controller;

import com.njfu.edu.pojo.Manager;
import com.njfu.edu.pojo.SubmitResult;
import com.njfu.edu.service.impl.ManagerServiceImpl;
import com.njfu.edu.utils.JDBCUtils;

import java.io.IOException;
import java.sql.Connection;

public class ManagerController {

    private ManagerServiceImpl managerService = new ManagerServiceImpl();

    public SubmitResult createManger(Manager manager) throws IOException {
        return managerService.createManger(manager);
    }
}
