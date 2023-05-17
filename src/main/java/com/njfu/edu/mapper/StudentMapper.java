package com.njfu.edu.mapper;

import com.njfu.edu.pojo.Paging;
import com.njfu.edu.pojo.Student;
import org.apache.ibatis.annotations.Param;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface StudentMapper {

    String SORT_BY_ID = "1";
    String SORT_BY_AGE = "2";
    String SORT_BY_SEX = "3";

    void insertStudent(@Param("student") Student student);
    List<Student> selectStudentMessage(@Param("paging") Paging paging);
    List<Student> selectStudentMessage(String filePath) throws IOException;
    void deleteStudentById(@Param("id") String id);
    void updateStudentMessage(@Param("student") Student student);
    long selectItems(@Param("paging") Paging paging) throws SQLException;
    Student selectStudentById(@Param("id") Long id);

    List<Student> selectStuInfo();
}
