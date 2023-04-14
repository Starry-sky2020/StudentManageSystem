package com.njfu.edu.utils;

import com.njfu.edu.pojo.Student;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CRUDUtilsTest {


    @Test
    public void testquery() throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        Connection connection = JDBCUtils.getConnection();
        String sql = "select lessontraining.student.student_name, lessontraining.studentclass.studentclassName, lessontraining.college.collegeName from lessontraining.student,lessontraining.studentclass,lessontraining.college\n" +
                "where lessontraining.studentclass.id = lessontraining.student.studentClassId \n" +
                "  and lessontraining.studentclass.collegeId = lessontraining.college.id;";
        List<Student> query = CRUDUtils.query(connection, Student.class, sql, null);

        for (Student student : query){
            System.out.println(student);
        }
    }
}
