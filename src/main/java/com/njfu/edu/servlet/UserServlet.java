package com.njfu.edu.servlet;

import com.alibaba.fastjson2.JSON;
import com.njfu.edu.pojo.SubmitResult;
import com.njfu.edu.pojo.User;
import com.njfu.edu.service.UserService;
import com.njfu.edu.service.impl.UserServiceImpl;
import com.njfu.edu.pojo.Ajax;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet({"/manager/staff","/manager/deluser","/usersubmit"})
public class UserServlet extends HttpServlet {

    private UserService userService = new UserServiceImpl();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        String servletPath = request.getServletPath();

        if (servletPath.equals("/manager/staff")){
            List<User> users = userService.selectAllUser();
            request.setAttribute("users",users);
            Ajax ajax = new Ajax();
            ajax.setListUser(users);
            String jsonString = JSON.toJSONString(ajax);
            writer.print(jsonString);
            writer.flush();
            writer.close();
        } else if (servletPath.equals("/manager/deluser")) {
            String userId = request.getParameter("user_id");
            System.out.println(userId);
            userService.deleteUserById(userId);
            System.out.println("ll"+userId);
//            request.getRequestDispatcher("/manager/staff").forward(request,response);
        } else if (servletPath.equals("/usersubmit")) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            Map map = new HashMap();
            map.put("username",username);
            map.put("password",password);

            SubmitResult submitResult = userService.userSubmit(map);
            request.setAttribute("userSubmit",submitResult);

//            request.getRequestDispatcher("/list/user-submit.jsp").forward(request,response);
        }
    }
}
