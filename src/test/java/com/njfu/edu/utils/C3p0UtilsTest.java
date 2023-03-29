package com.njfu.edu.utils;

import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class C3p0UtilsTest {

    @Test
    public void testC3p0Utils() throws SQLException {
        Connection connection1 = C3p0Utils.getConnection();
        Connection connection2 = C3p0Utils.getConnection();
        Connection connection3 = C3p0Utils.getConnection();
        Connection connection4 = C3p0Utils.getConnection();
        Connection connection5 = C3p0Utils.getConnection();
//        PreparedStatement preparedStatement = connection2.prepareStatement("select * from lessontraining.student");
//        ResultSet resultSet = preparedStatement.executeQuery();
//        while (resultSet.next()){
//            System.out.println(resultSet.getString("student_name"));
//        }
        System.out.println(connection1);
        System.out.println(connection2);
        System.out.println(connection3);
        System.out.println(connection4);
        System.out.println(connection5);

        Connection connection6 = C3p0Utils.getConnection();
        System.out.println(connection6);
    }
}
