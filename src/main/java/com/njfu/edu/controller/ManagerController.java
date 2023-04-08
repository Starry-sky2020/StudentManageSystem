package com.njfu.edu.controller;

import com.njfu.edu.pojo.Manager;
import com.njfu.edu.pojo.SubmitResult;
import com.njfu.edu.service.impl.ManagerServiceImpl;
import jakarta.servlet.annotation.WebServlet;

import java.io.IOException;

public class ManagerController {

    private ManagerServiceImpl managerService = new ManagerServiceImpl();

    public SubmitResult createManger(Manager manager) throws IOException {
        return managerService.createManger(manager);
    }


}
