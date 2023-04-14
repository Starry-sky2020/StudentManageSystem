package com.njfu.edu.service.impl;

import com.njfu.edu.dao.StudentClassDao;
import com.njfu.edu.dao.impl.StudentClassDaoImpl;
import com.njfu.edu.pojo.StudentClass;
import com.njfu.edu.service.StudentClassService;
import com.njfu.edu.utils.JDBCUtils;

import java.sql.Connection;
import java.util.List;

public class StudentClassServiceImpl implements StudentClassService {

    private StudentClassDao studentClassDao = new StudentClassDaoImpl();

    @Override
    public List<StudentClass> queryStudentClassById(int collegeId) {
        Connection connection = JDBCUtils.getConnection();
        List<StudentClass> studentClasses = studentClassDao.queryStudentClassById(connection, collegeId);
        JDBCUtils.connRelease(connection);
        return studentClasses;
    }

    @Override
    public List<StudentClass> queryStudentClass() {
        Connection connection = JDBCUtils.getConnection();
        List<StudentClass> studentClasses = studentClassDao.queryStudentClass(connection);
        JDBCUtils.connRelease(connection);
        return studentClasses;
    }
}
