package com.njfu.edu.utils;

import java.sql.*;
import java.util.ResourceBundle;

public class JDBCUtils {

    private static String driver;
    private static String url;
    private static String username;
    private static String password;

    /**
     * 获取所连接的数据库信息
     */
    static {
        ResourceBundle bundle = ResourceBundle.getBundle("jdbc");
        driver = bundle.getString("jdbc.driver");
        url = bundle.getString("jdbc.url");
        username = bundle.getString("jdbc.username");
        password = bundle.getString("jdbc.password");
    }

    /**
     * 连接数据库
     * @return
     */
    public static Connection getConnection(){
        Connection connection = null;

        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url,username,password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * 关闭数据库
     * @param connection
     * @param ps
     * @param resultSet
     */
    public static void Release(Connection connection, PreparedStatement ps, ResultSet resultSet){

        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        if (ps != null){
            try {
                ps.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        if (connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
