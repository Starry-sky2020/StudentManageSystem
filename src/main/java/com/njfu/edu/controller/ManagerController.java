package com.njfu.edu.controller;

import com.njfu.edu.pojo.Manager;
import com.njfu.edu.service.ManagerService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;

@Controller
public class ManagerController {

    @Autowired
    private ManagerService managerService;

    @GetMapping("/manager/createmanager/{mName}/{mPassword}/{mRemark}")
    @PostMapping
    public void createManager(@PathVariable("mName") String mName,
                              @PathVariable("mPassword") String mPassword,
                              @PathVariable("mRemark") String mRemark,
                              HttpServletRequest request) throws IOException {
        Manager manager = new Manager();
        manager.setManager_name(mName);
        manager.setPassword(mPassword);
        manager.setRemarks(mRemark);

        request.setAttribute("resManager",managerService.createManger(manager));
    }
}
