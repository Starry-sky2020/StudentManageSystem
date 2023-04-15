package com.njfu.edu.utils;

import com.njfu.edu.pojo.Student;
import com.njfu.edu.pojo.User;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CRUDUtilsTest {


    @Test
    public void testquery(){
        Connection connection = JDBCUtils.getConnection();
//        String sql = "select lessontraining.student.student_name, lessontraining.studentclass.studentclassName, lessontraining.college.collegeName from lessontraining.student,lessontraining.studentclass,lessontraining.college\n" +
//                "where lessontraining.studentclass.id = lessontraining.student.studentClassId \n" +
//                "  and lessontraining.studentclass.collegeId = lessontraining.college.id;";

        String sql = "select * from lessontraining.user";
        List<User> query = CRUDUtils.query(connection, User.class, sql, null);

        for (User user : query){
            System.out.println(user);
        }
    }
}
