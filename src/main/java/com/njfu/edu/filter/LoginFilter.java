package com.njfu.edu.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String servletPath = request.getServletPath();
        HttpSession session = request.getSession();
        Object identity = session.getAttribute("identity");
        System.out.println(servletPath);
        if (servletPath.equals("/list/managerLogin.jsp") || servletPath.equals("/managerlogin")
            || servletPath.equals("/list/userLogin.jsp") || servletPath.equals("/userlogin")
            || servletPath.equals("/list/user-submit.jsp") || servletPath.equals("/usersubmit")
            || servletPath.equals("/list/error.html") ){
            filterChain.doFilter(request,response);
        } else {
            if (identity == null){
                response.sendRedirect(request.getContextPath()+"/list/managerLogin.jsp");
            } else {
                filterChain.doFilter(request,response);
            }
        }
    }
}
