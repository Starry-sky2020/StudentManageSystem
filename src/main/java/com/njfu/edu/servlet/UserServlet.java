package com.njfu.edu.servlet;

import com.alibaba.fastjson2.JSON;
import com.njfu.edu.pojo.SubmitResult;
import com.njfu.edu.pojo.User;
import com.njfu.edu.service.UserService;
import com.njfu.edu.service.impl.UserServiceImpl;
import com.njfu.edu.pojo.Ajax;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@WebServlet({"/manager/staff","/manager/deluser","/usersubmit"})
public class UserServlet extends HttpServlet {

    @Autowired
    private UserService userService;

    @Override
    public void init(ServletConfig config) {
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }

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
            userService.deleteUserById(userId);
        } else if (servletPath.equals("/usersubmit")) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            Map map = new HashMap();
            map.put("username",username);
            map.put("password",password);

            SubmitResult submitResult = userService.userSubmit(map);
            request.setAttribute("userSubmit",submitResult);
        }
    }
}
