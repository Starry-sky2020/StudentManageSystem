package com.njfu.edu.controller;

import com.njfu.edu.pojo.SubmitResult;
import com.njfu.edu.pojo.User;
import com.njfu.edu.service.impl.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet({"/manager/staff"})
public class UserController extends HttpServlet {

    private UserServiceImpl userService = new UserServiceImpl();


    public List<User> selectAllUser() throws IOException {
        return userService.selectAllUser();
    }

    public void deleteUserById(String id) throws IOException {
        userService.deleteUserById(id);
    }

    public SubmitResult userSubmit(Map<String, String> map) throws IOException {
        return userService.userSubmit(map);
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String servletPath = request.getServletPath();

        if (servletPath.equals("/manager/staff")){
            List<User> users = selectAllUser();
            request.setAttribute("users",users);
            System.out.println(users);
            request.getRequestDispatcher("/userList.jsp").forward(request,response);
        }
    }
}
