package com.njfu.edu.dao;

import com.njfu.edu.pojo.College;
import com.njfu.edu.pojo.StudentClass;

import java.sql.Connection;
import java.util.List;

public interface StudentClassDao {
    /**
     * 查询学生班级通过collegeId
     * @param connection
     * @param collegeId
     * @return
     */
    List<StudentClass> queryStudentClassById(Connection connection, int collegeId);

    /**
     * 查询学生班级
     * @param connection
     * @return
     */
    List<StudentClass> queryStudentClass(Connection connection);
}
