package com.njfu.edu.controller;

import com.njfu.edu.service.impl.CheckPersonServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet({"/userlogin","/managerlogin","/test"})
public class CheckPersonController extends HttpServlet {

    private CheckPersonServiceImpl checkPersonServiceImpl = new CheckPersonServiceImpl();

    public Boolean UserLoginView(Map<String,String> map) throws IOException {
        return checkPersonServiceImpl.UserLoginView(map);
    }

    public Boolean ManagerLoginView(Map<String,String> map) throws IOException {
        return checkPersonServiceImpl.ManagerLoginView(map);
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String servletPath = request.getServletPath();
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        HttpSession session = request.getSession();

        if (servletPath.equals("/managerlogin")){
            Map<String,String> map = new HashMap<>();
            map.put("managername",username);
            map.put("password",password);
            if (ManagerLoginView(map)){
                session.setAttribute("identity",0);
                response.sendRedirect("/StudentManageSystem/list/manager-index.jsp");
            } else {
                response.sendRedirect("/StudentManageSystem/error.html");
            }

        } else if (servletPath.equals("/userlogin")) {
            Map<String,String> map = new HashMap<>();
            map.put("username",username);
            map.put("password",password);
            if (UserLoginView(map)){
                session.setAttribute("identity",1);
                response.sendRedirect("/StudentManageSystem/list/manager-index.jsp");
            } else {
                response.sendRedirect("/StudentManageSystem/error.html");
            }
        }
    }
}
