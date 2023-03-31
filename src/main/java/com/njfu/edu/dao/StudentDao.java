package com.njfu.edu.dao;

import com.njfu.edu.pojo.CheckStudentFormatResult;
import com.njfu.edu.pojo.Student;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface StudentDao {
    void insertStudent(Student student);
    List<Student> selectStudentMessage();
    List<Student> selectStudentMessage(String filePath) throws IOException;
    CheckStudentFormatResult checkStudentFormat(String filePath);
    Boolean checkRepeatData(List<Student> list, String id);
    void deleteStudentById(String id);
    long getItemNums() throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException;
    void updateStudentMessage(Student student);
}
