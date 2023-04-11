package com.njfu.edu.dao;

import com.njfu.edu.dao.impl.ManagerDaoImpl;
import com.njfu.edu.dao.impl.StudentDaoImpl;
import com.njfu.edu.pojo.Manager;
import com.njfu.edu.pojo.Paging;
import com.njfu.edu.pojo.Student;
import com.njfu.edu.utils.JDBCUtils;
import com.njfu.edu.utils.Tools;
import org.junit.Test;

import java.sql.Connection;
import java.text.ParseException;
import java.util.List;

public class StudentDaoTest {

    @Test
    public void testSelectStudentById(){
        Connection connection = JDBCUtils.getConnection();
        StudentDaoImpl studentDao = new StudentDaoImpl();
        System.out.println( studentDao.selectStudentById(connection,1));
    }

    @Test
    public void testUpdateStu() throws ParseException {
        Connection connection = JDBCUtils.getConnection();
        StudentDaoImpl studentDao = new StudentDaoImpl();
        Student student = new Student();
        student.setStudent_id(String.valueOf(14));
        student.setUpdateTime(Tools.getCurrentSystemDate());
        student.setStudent_name("高适");
        student.setAddress("重庆");
        student.setSex(1);
        student.setInfo("无");
        student.setDeleteFlag(1);
        studentDao.updateStudentMessage(connection,student);
    }

    @Test
    public void testselect() throws ParseException {
        Connection connection = JDBCUtils.getConnection();
        StudentDaoImpl studentDao = new StudentDaoImpl();
//        Paging<Student> paging = new Paging<>();
//        List<Student> students = studentDao.selectStudentMessage(connection, paging);
        ManagerDaoImpl managerDao = new ManagerDaoImpl();
        List<Manager> managers = managerDao.selectManagerMessage(connection);
        System.out.println(managers);
    }
}
