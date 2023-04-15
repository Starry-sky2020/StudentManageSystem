package com.njfu.edu.controller;

import com.njfu.edu.service.ManagerService;
import com.njfu.edu.service.UserService;
import com.njfu.edu.service.impl.CheckPersonServiceImpl;
import com.njfu.edu.service.impl.ManagerServiceImpl;
import com.njfu.edu.service.impl.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet({"/userlogin","/managerlogin","/exitsystem"})
public class CheckPersonController extends HttpServlet {

    public static Long id;
    public static Boolean indentity;
    private CheckPersonServiceImpl checkPersonServiceImpl = new CheckPersonServiceImpl();
    private ManagerService managerService = new ManagerServiceImpl();
    private UserService userService = new UserServiceImpl();

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

        String contextPath = request.getContextPath();
        HttpSession session = request.getSession();
;
        if (servletPath.equals("/managerlogin")){  //管理员登录
            Map<String,String> map = new HashMap<>();
            map.put("managername",username);
            map.put("password",password);
            if (ManagerLoginView(map)){
                session.setAttribute("identity",0);
                session.setAttribute("loginName",username);
                id = managerService.selectManagerIdByPhone(password);
                indentity = true;
                response.sendRedirect(contextPath+"/list/manager-index.jsp");
            } else {
                response.sendRedirect(contextPath+"/error.html");
            }

        } else if (servletPath.equals("/userlogin")) {  //用户登录
            Map<String,String> map = new HashMap<>();
            map.put("username",username);
            map.put("password",password);
            if (UserLoginView(map)){
                session.setAttribute("identity",1);
                session.setAttribute("loginName",username);
                id = userService.selectUserIdByPhone(password);
                indentity = false;
                response.sendRedirect(contextPath+"/list/manager-index.jsp");
            } else {
                response.sendRedirect(contextPath+"/error.html");
            }
        } else if (servletPath.equals("/exitsystem")) {  //退出系统
            session.invalidate();  //销毁session
            response.sendRedirect(contextPath+"/managerLogin.jsp");
        }
    }
}
