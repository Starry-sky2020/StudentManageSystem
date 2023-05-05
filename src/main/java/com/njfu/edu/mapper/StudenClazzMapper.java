package com.njfu.edu.mapper;

import com.njfu.edu.pojo.StudentClass;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudenClazzMapper {

    /**
     * 查询学生班级通过collegeId
     * @param id
     * @return
     */
    List<StudentClass> queryStudentClassById(@Param("id") Integer id);

    /**
     * 查询学生班级
     * @return
     */
    List<StudentClass> queryStudentClass();
}
