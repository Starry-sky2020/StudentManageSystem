package com.njfu.edu.dao.impl;

import com.njfu.edu.dao.StudentClassDao;
import com.njfu.edu.pojo.StudentClass;
import com.njfu.edu.utils.CRUDUtils;

import java.sql.Connection;
import java.util.List;

public class StudentClassDaoImpl implements StudentClassDao {
    @Override
    public List<StudentClass> queryStudentClassById(Connection connection, int collegeId) {
        String sql = "select * from studentClass where collegeId=? order by id;";
        return CRUDUtils.query(connection,StudentClass.class,sql,collegeId);
    }

    @Override
    public List<StudentClass> queryStudentClass(Connection connection) {
        String sql = "select * from studentClass";
        return CRUDUtils.query(connection, StudentClass.class,sql,null);
    }
}
