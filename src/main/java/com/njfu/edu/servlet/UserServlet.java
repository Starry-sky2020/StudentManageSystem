package com.njfu.edu.servlet;

import com.njfu.edu.pojo.SubmitResult;
import com.njfu.edu.pojo.User;
import com.njfu.edu.service.UserService;
import com.njfu.edu.service.impl.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet({"/manager/staff","/manager/deluser","/usersubmit"})
public class UserServlet extends HttpServlet {

    private UserService userService = new UserServiceImpl();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String servletPath = request.getServletPath();

        if (servletPath.equals("/manager/staff")){
            List<User> users = userService.selectAllUser();
            request.setAttribute("users",users);

            request.getRequestDispatcher("/list/manager-userList.jsp").forward(request,response);
        } else if (servletPath.equals("/manager/deluser")) {
            String userId = request.getParameter("user_id");
            userService.deleteUserById(userId);

            request.getRequestDispatcher("/manager/staff").forward(request,response);
        } else if (servletPath.equals("/usersubmit")) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            Map map = new HashMap();
            map.put("username",username);
            map.put("password",password);

            SubmitResult submitResult = userService.userSubmit(map);
            request.setAttribute("userSubmit",submitResult);

            request.getRequestDispatcher("/list/user-submit.jsp").forward(request,response);
        }
    }
}
