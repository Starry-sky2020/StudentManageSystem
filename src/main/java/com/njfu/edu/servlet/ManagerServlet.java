package com.njfu.edu.servlet;

import com.njfu.edu.pojo.Manager;
import com.njfu.edu.pojo.SubmitResult;
import com.njfu.edu.service.impl.ManagerServiceImpl;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import java.io.IOException;

@WebServlet({"/manager/createmanager"})
public class ManagerServlet extends HttpServlet {

    @Autowired
    private ManagerServiceImpl managerService;

    @Override
    public void init(ServletConfig config) {
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }

    public SubmitResult createManger(Manager manager) throws IOException {
        return managerService.createManger(manager);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String mName = request.getParameter("mName");
        String mPassword = request.getParameter("mPassword");
        String mRemark = request.getParameter("mRemark");

        Manager manager = new Manager();
        manager.setManager_name(mName);
        manager.setPassword(mPassword);
        manager.setRemarks(mRemark);

        request.setAttribute("resManager",createManger(manager));
    }
}
