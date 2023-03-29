package com.njfu.edu.utils;

import junit.framework.TestCase;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCUtilsTest extends TestCase {

    @Test
    public void testJdbc(){
        //测试数据库连接
        Connection connection = JDBCUtils.getConnection();

        String sql = "select * from lessontraining.student";

        PreparedStatement preparedStatement;

        {
            try {
                preparedStatement = connection.prepareStatement(sql);
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()){
                    String studentName = resultSet.getString("student_name");
                    System.out.println(studentName);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

}