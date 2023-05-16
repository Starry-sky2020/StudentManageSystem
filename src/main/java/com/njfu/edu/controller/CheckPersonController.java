package com.njfu.edu.controller;

import com.njfu.edu.service.CheckPersonService;
import com.njfu.edu.service.ManagerService;
import com.njfu.edu.service.UserService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Controller
//@WebServlet({"/userlogin","/managerlogin","/exitsystem"})
public class CheckPersonController {

    //全局静态变量，获取id和等录系统的身份（管理员/用户）
    //为了记录系统运行日志
    public static Long id;
    public static Boolean indentity;
    @Autowired
    private CheckPersonService checkPersonService;
    @Autowired
    private ManagerService managerService;
    @Autowired
    private UserService userService;

    //spring整合tomcat 将对象控制权从tomcat转到spring
//    @Override
//    public void init(ServletConfig config) {
//        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
//    }
//

    @GetMapping("/managerlogin/{username}/{password}")
    @ResponseBody
    public String managerLogin(@PathVariable("username") String username,
                               @PathVariable("password") String password,
                               HttpSession session) throws IOException {
        Map<String,String> map = new HashMap<>();
        map.put("managername",username);
        map.put("password",password);

        if (checkPersonService.ManagerLoginView(map)){
            session.setAttribute("identity",0);
            session.setAttribute("loginName",username);
            id = managerService.selectManagerIdByPhone(password);
            indentity = true;
            return "OK";
        } else return "ERROR";
    }

    @GetMapping("/userlogin/{username}/{password}")
    @ResponseBody
    public String userLogin(@PathVariable("username") String username,
                            @PathVariable("password") String password,
                            HttpSession session) throws IOException {
        Map<String,String> map = new HashMap<>();
        map.put("username",username);
        map.put("password",password);

        if (checkPersonService.UserLoginView(map)){
            session.setAttribute("identity",1);
            session.setAttribute("loginName",username);
            id = userService.selectUserIdByPhone(password);
            indentity = false;
            return "OK";
        } else return "ERROR";
    }

    @GetMapping("/exitsystem")
    public String exitSystem(){
        return "managerLogin";
    }
}
