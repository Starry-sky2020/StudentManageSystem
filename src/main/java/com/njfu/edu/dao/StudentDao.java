package com.njfu.edu.dao;

import com.njfu.edu.pojo.Paging;
import com.njfu.edu.pojo.Student;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface StudentDao {

    String SORT_BY_ID = "1";
    String SORT_BY_AGE = "2";
    String SORT_BY_SEX = "3";

    void insertStudent(Student student);
    List<Student> selectStudentMessage(Paging paging);
    List<Student> selectStudentMessage(String filePath) throws IOException;
    void deleteStudentById(String id);
    void updateStudentMessage(Student student);
    long selectItems(Paging paging) throws SQLException;
}
