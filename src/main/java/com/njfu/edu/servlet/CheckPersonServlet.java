package com.njfu.edu.servlet;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

//不需加Controller注解 Controller和WebServlet有重合
@WebServlet({"/userlogin","/managerlogin","/exitsystem"})
public class CheckPersonServlet extends HttpServlet {

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
    @Override
    public void init(ServletConfig config) {
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String servletPath = request.getServletPath();
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        String contextPath = request.getContextPath();
        HttpSession session = request.getSession();

        if (servletPath.equals("/managerlogin")){  //管理员登录
            Map<String,String> map = new HashMap<>();
            map.put("managername",username);
            map.put("password",password);

            if (checkPersonService.ManagerLoginView(map)){
                session.setAttribute("identity",0);
                session.setAttribute("loginName",username);
                id = managerService.selectManagerIdByPhone(password);
                indentity = true;
                writer.write("OK");
            } else writer.write("ERROR");
            writer.flush();
            writer.close();
        } else if (servletPath.equals("/userlogin")) {  //用户登录
            Map<String,String> map = new HashMap<>();
            map.put("username",username);
            map.put("password",password);

            if (checkPersonService.UserLoginView(map)){
                session.setAttribute("identity",1);
                session.setAttribute("loginName",username);
                id = userService.selectUserIdByPhone(password);
                indentity = false;
                writer.print("OK");
            } else writer.print("ERROR");
            writer.flush();
            writer.close();
        } else if (servletPath.equals("/exitsystem")) {  //退出系统
            session.invalidate();  //销毁session
            response.sendRedirect(contextPath+"/list/managerLogin.jsp");
        }
    }
}
