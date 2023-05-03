package com.njfu.edu.servlet;

import com.alibaba.fastjson2.JSON;
import com.njfu.edu.pojo.Paging;
import com.njfu.edu.pojo.Student;
import com.njfu.edu.service.CollegeService;
import com.njfu.edu.service.StudentClassService;
import com.njfu.edu.service.StudentService;
import com.njfu.edu.service.impl.CollegeServiceImpl;
import com.njfu.edu.service.impl.StudentClassServiceImpl;
import com.njfu.edu.service.impl.StudentServiceImpl;
import com.njfu.edu.pojo.Ajax;
import com.njfu.edu.utils.Tools;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.*;
import java.text.ParseException;
import java.util.*;

@WebServlet({"/stulist","/delstu","/updatestu","/selectstuid","/addstu"})
public class StudentServlet extends HttpServlet {

    private StudentService studentService = new StudentServiceImpl();
    private CollegeService collegeService = new CollegeServiceImpl();
    private StudentClassService classService = new StudentClassServiceImpl();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String servletPath = request.getServletPath();
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = response.getWriter();

        if (servletPath.equals("/stulist")){ //学生信息列表
            Paging<Student> paging = new Paging<>();
            HttpSession session = request.getSession();
            Map map = new HashMap();

            String pageNum = request.getParameter("pageNum");
            String condition = request.getParameter("condition");
            String collegeName = request.getParameter("collegeName");
            String clazzName = request.getParameter("clazzName");
            if (collegeName != null) {
                map.put("collegeName",collegeName);
                session.setAttribute("map_name",map);
            }
            if (clazzName != null) {
                map.put("clazzName",clazzName);
                session.setAttribute("map_name",map);
            }
            boolean falg = false;
            if (condition != null){
                if (condition.equals("1"))  falg=true;
                if (condition.equals("2")) map.put("key","2");
                if (condition.equals("3")) map.put("key","3");
                session.setAttribute("map",map);
            }

            //session域 记忆选择条件
            if (session.getAttribute("map") != null)
                map.putAll((Map) session.getAttribute("map"));
            if (session.getAttribute("map_name") != null)
                map.putAll((Map) session.getAttribute("map_name"));
            if (falg) map.clear();
            paging.setMap(map);

            if (pageNum != null) paging.setPageNum(Integer.valueOf(pageNum));
            studentService.selectAllStudent(paging);

            request.setAttribute("college",collegeService.queryAllCollege());
            request.setAttribute("clazz", classService.queryStudentClass());
            request.setAttribute("paging",paging);

            Ajax ajax = new Ajax();
            ajax.setListStu(paging.getList());
            ajax.setState(1);
            ajax.setPaging(paging);
            ajax.setListCollege(collegeService.queryAllCollege());
            ajax.setListClazz(classService.queryStudentClass());
            String jsonString = JSON.toJSONString(ajax);

            writer.print(jsonString);
            writer.flush();
            writer.close();
        } else if (servletPath.equals("/delstu")){ //删除学生
            String id = request.getParameter("id");
            studentService.DeleteStudentById(id);
            writer.print("ok");
        } else if (servletPath.equals("/selectstuid")) {  //根据id查询学生
            String id = request.getParameter("id");
            Student student = studentService.selectStudetById(Long.parseLong(id));
            request.setAttribute("updateStu",student);
            request.getRequestDispatcher("/list/manager-updateStu.jsp").forward(request,response);
        } else if (servletPath.equals("/updatestu")) { //修改学生信息
            String stuId = request.getParameter("stuId");
            String stuName = request.getParameter("stuName");
            Integer stuAge = Integer.valueOf(request.getParameter("stuAge"));
            Integer stuSex = Integer.valueOf(request.getParameter("stuSex"));
            String stuSch = request.getParameter("stuSch");
            String stuAdd = request.getParameter("stuAdd");
            String stuInfo = request.getParameter("stuInfo");

            Student student = new Student();
            student.setStudent_id(Long.valueOf(stuId));
            student.setStudent_name(stuName);
            student.setAge(stuAge);
            student.setSex(stuSex);
            student.setSchool(stuSch);
            student.setAddress(stuAdd);
            student.setInfo(stuInfo);
            student.setDeleteFlag(1);
            System.out.println(student);
            try {
                student.setUpdateTime(Tools.getCurrentSystemDate());
                studentService.changeStudentInfo(student);
            } catch (ParseException e) {
                e.printStackTrace();
            }


            request.getRequestDispatcher("/stulist").forward(request,response);
        } else if (servletPath.equals("/addstu")) {  //添加学生
            String stuName = request.getParameter("stuName");
            Integer stuAge = Integer.valueOf(request.getParameter("stuAge"));
            String stuSex = request.getParameter("stuSex");
            String stuSch = request.getParameter("stuSch");
            String stuAdd = request.getParameter("stuAdd");
            String stuClazz = request.getParameter("stuClazz");

            Student student = new Student();
            student.setStudent_name(stuName);
            student.setAge(stuAge);
            if (stuSex.equals("男")) student.setSex(1);
            else student.setSex(0);
            student.setSchool(stuSch);
            student.setAddress(stuAdd);
            student.setStudentClassId(Integer.valueOf(stuClazz));

            studentService.InsertStudentMessage(student);
        }
    }
}