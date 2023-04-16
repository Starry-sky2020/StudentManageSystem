package com.njfu.edu.dao;

import com.njfu.edu.pojo.Paging;
import com.njfu.edu.pojo.Student;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface StudentDao {

    String SORT_BY_ID = "1";
    String SORT_BY_AGE = "2";
    String SORT_BY_SEX = "3";

    void insertStudent(Connection connection,Student student);
    List<Student> selectStudentMessage(Connection connection,Paging paging);
    List<Student> selectStudentMessage(String filePath) throws IOException;
    void deleteStudentById(Connection connection,String id);
    void updateStudentMessage(Connection connection,Student student);
    long selectItems(Connection connection,Paging paging) throws SQLException;
    Student selectStudentById(Connection connection, Long id);
}
