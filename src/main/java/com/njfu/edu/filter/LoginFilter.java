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
        if (servletPath.equals("/managerLogin.jsp") || servletPath.equals("/managerlogin")
            || servletPath.equals("/userLogin.jsp") || servletPath.equals("/userlogin")
            || servletPath.equals("/user-submit.jsp") || servletPath.equals("/usersubmit")){
            filterChain.doFilter(request,response);
        } else {
            if (identity == null){
                response.sendRedirect(request.getContextPath()+"/managerLogin.jsp");
            } else {
                filterChain.doFilter(request,response);
            }
        }
    }
}
