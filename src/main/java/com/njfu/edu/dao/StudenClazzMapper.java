package com.njfu.edu.dao;

import com.njfu.edu.pojo.StudentClass;

import java.sql.Connection;
import java.util.List;

public interface StudenClazzMapper {

    /**
     * 查询学生班级通过collegeId
     * @param collegeId
     * @return
     */
    List<StudentClass> queryStudentClassById(int collegeId);

    /**
     * 查询学生班级
     * @return
     */
    List<StudentClass> queryStudentClass();
}
