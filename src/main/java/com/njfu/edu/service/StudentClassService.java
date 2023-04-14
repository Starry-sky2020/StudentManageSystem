package com.njfu.edu.service;

import com.njfu.edu.pojo.StudentClass;

import java.util.List;

public interface StudentClassService {

    /**
     * 查询班级 通过collegeId
     * @param collegeId
     * @return
     */
    List<StudentClass> queryStudentClassById(int collegeId);

    /**
     * 查询班级
     * @return
     */
    List<StudentClass> queryStudentClass();
}
