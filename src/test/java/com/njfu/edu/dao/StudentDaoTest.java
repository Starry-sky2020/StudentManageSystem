package com.njfu.edu.dao;

import com.njfu.edu.dao.impl.StudentDaoImpl;
import com.njfu.edu.utils.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;

public class StudentDaoTest {

    @Test
    public void testSelectStudentById(){
        Connection connection = JDBCUtils.getConnection();
        StudentDaoImpl studentDao = new StudentDaoImpl();
        System.out.println( studentDao.selectStudentById(connection,1));
    }
}
