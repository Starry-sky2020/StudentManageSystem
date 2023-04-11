package com.njfu.edu.controller;

import com.njfu.edu.pojo.ImportResult;
import com.njfu.edu.pojo.Paging;
import com.njfu.edu.pojo.Student;
import com.njfu.edu.service.impl.StudentServiceImpl;
import com.njfu.edu.utils.Tools;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;
import java.text.ParseException;
import java.util.*;

@WebServlet({"/stulist","/delstu","/updatestu","/selectstuid"})
public class StudentController extends HttpServlet {

    private StudentServiceImpl studentService = new StudentServiceImpl();

    /**
     * 查询所有学生
     * @param paging
     * @return
     * @throws IOException
     */
    public void selectAllStudent(Paging paging) throws IOException {
        studentService.selectAllStudent(paging);
    }

    /**
     * 根据学号查询学生信息
     * @param id
     * @return
     * @throws IOException
     */
    public Student selectStudetById(long id) throws IOException {
       return studentService.selectStudetById(id);
    }

    /**
     * 更新学生信息
     * @param student
     * @throws IOException
     */
    public void UpdateStudentById(Student student)
            throws IOException, ParseException {
        studentService.changeStudentInfo(student);
    }

    /**
     * 根据学生Id排序
     * 冒泡排序
     * @return
     * @throws IOException
     */
    public List<Student> SortByStudetId(Paging paging) throws IOException {
       return studentService.SortByStudetId(paging);
    }

    /**
     * 导入数据
     * 将新数据导入到原始文件中
     * @param path
     * @throws IOException
     */
    public ImportResult ImportStudentMessage(String path) throws IOException {
        return studentService.ImportStudentMessage(path);
    }

    /**
     * 插入学生信息
     * 根据Student参数，获取插入文件的信息
     * 将信息直接写（追加方式）到文件中
     * @param student
     * @throws IOException
     */
    public void InsertStudentMessage(Student student) throws IOException {
        studentService.InsertStudentMessage(student);
    }

    /**
     * 在列表中将元素删除
     * 重新写入到文件中
     * @param id
     * @throws IOException
     */
    public void DeleteStudentById(String id) throws IOException {
       studentService.DeleteStudentById(id);
    }

    /**
     * 退出系统
     */
    public void BackwardSystem() {
        studentService.BackwardSystem();
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String servletPath = request.getServletPath();

        if (servletPath.equals("/stulist")){ //学生信息列表
            Paging<Student> paging = new Paging<>();
            String pageNum = request.getParameter("pageNum");
            if (pageNum != null) paging.setPageNum(Integer.valueOf(pageNum));
            selectAllStudent(paging);
            request.setAttribute("paging",paging);
            request.getRequestDispatcher("/list/stuList.jsp").forward(request,response);
        } else if (servletPath.equals("/delstu")){ //删除学生
            String id = request.getParameter("id");
            DeleteStudentById(id);
            request.getRequestDispatcher("/stulist").forward(request,response);
        } else if (servletPath.equals("/selectstuid")) {  //根据id查询学生
            String id = request.getParameter("id");
            Student student = selectStudetById(Long.parseLong(id));
            request.setAttribute("updateStu",student);
            request.getRequestDispatcher("/list/updateStu.jsp").forward(request,response);
        } else if (servletPath.equals("/updatestu")) { //修改学生信息
            String stuId = request.getParameter("stuId");
            String stuName = request.getParameter("stuName");
            Integer stuAge = Integer.valueOf(request.getParameter("stuAge"));
            Integer stuSex = Integer.valueOf(request.getParameter("stuSex"));
            String stuSch = request.getParameter("stuSch");
            String stuAdd = request.getParameter("stuAdd");
            String stuInfo = request.getParameter("stuInfo");

            Student student = new Student();
            student.setStudent_id(stuId);
            student.setStudent_name(stuName);
            student.setAge(stuAge);
            student.setSex(stuSex);
            student.setSchool(stuSch);
            student.setAddress(stuAdd);
            student.setInfo(stuInfo);

            try {
                student.setUpdateTime(Tools.getCurrentSystemDate());
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }

            try {
                UpdateStudentById(student);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            request.getRequestDispatcher("/stulist").forward(request,response);
        }
    }
}