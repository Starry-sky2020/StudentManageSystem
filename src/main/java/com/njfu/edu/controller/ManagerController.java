package com.njfu.edu.controller;

import com.njfu.edu.pojo.Manager;
import com.njfu.edu.pojo.SubmitResult;
import com.njfu.edu.service.impl.ManagerServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet({"/manager/createmanager"})
public class ManagerController extends HttpServlet {

    private ManagerServiceImpl managerService = new ManagerServiceImpl();

    public SubmitResult createManger(Manager manager) throws IOException {
        return managerService.createManger(manager);
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String mName = request.getParameter("mName");
        String mPassword = request.getParameter("mPassword");
        String mRemark = request.getParameter("mRemark");

        Manager manager = new Manager();
        manager.setManager_name(mName);
        manager.setPassword(mPassword);
        manager.setRemarks(mRemark);

        request.setAttribute("resManager",createManger(manager));
        request.getRequestDispatcher("/list/manager-createManager.jsp").forward(request,response);
    }
}
