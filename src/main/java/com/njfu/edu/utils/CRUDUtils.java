package com.njfu.edu.utils;

import jdk.nashorn.internal.scripts.JD;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CRUDUtils<T> {

    public static<T> long selectItems(Class<T> tClass, String sql, Object... params) throws SQLException {
        Connection connection = JDBCUtils.getConnection();
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            connection.setAutoCommit(false);
            for (int i = 0; params != null && i < params.length; i++){
                preparedStatement.setObject(i+1,params[i]);
            }
            resultSet = preparedStatement.executeQuery();
            connection.commit();
        } catch (SQLException e) {
            if (connection != null)
                    connection.rollback();
            throw new RuntimeException(e);
        } finally {
            JDBCUtils.release(connection,preparedStatement,resultSet);
        }
        long value = 0;
        if(resultSet.next()){
            value = (long) resultSet.getObject(1);
        }

        return value;
    }
    /**
     * 查询信息
     * @param tClass
     * @param sql
     * @param params
     * @return
     * @param <T>
     */
    public static <T> List<T> query(Class<T> tClass, String sql, Object... params){

        List<T> result = new ArrayList<>();
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            connection.setAutoCommit(false);
            for (int i = 0; params != null && i < params.length; i++){
                preparedStatement.setObject(i+1,params[i]);
            }

            resultSet = preparedStatement.executeQuery();
            connection.commit();
            //取出集合结果中的列名
            ResultSetMetaData metaData = resultSet.getMetaData();
            List<String> list = new ArrayList<>();
            // 获取resultSet中的列数
            int cnt = metaData.getColumnCount();
            for (int i = 1; i < cnt; i++){
                //获取第i列信息
                String columnLabel = metaData.getColumnLabel(i);
                //将第i列信息加入数组
                list.add(columnLabel);
            }

            //解析结果集
            while (resultSet.next()){
                T t = tClass.newInstance();
                Iterator<String> iterator = list.iterator();

                while (iterator.hasNext()){
                    String columnLabel = iterator.next();

                    Object value = resultSet.getObject(columnLabel);
                    //解决pojo类和数据库字段类型不匹配问题
                    if (columnLabel.equals("student_id") || columnLabel.equals("manager_id") || columnLabel.equals("user_id"))
                        value = String.valueOf(value);

                    Field declaredField = tClass.getDeclaredField(columnLabel);
                    declaredField.setAccessible(true);
                    declaredField.set(t,value);
                }

                result.add(t);
            }
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
            throw new RuntimeException();
        } catch (NoSuchFieldException | IllegalAccessException e){
            e.printStackTrace();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtils.release(connection,preparedStatement,resultSet);
        }

        return result;
    }

    /**
     * 插入数据
     * @param sql
     * @param data
     * @return
     */
    public static int insert(String sql, Object... data){
        int i;
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            connection.setAutoCommit(false);
            int cnt = 1;
            for (Object elems : data){
                preparedStatement.setObject(cnt++,elems);
            }
            i = preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
            throw new RuntimeException(e);
        } finally {
            JDBCUtils.release(connection,preparedStatement,null);
        }

        return i;
    }

    /**
     * 删除数据
     * @param sql
     * @param data
     * @return
     */
    public int delete(String sql, Object... data){
        int res;
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            connection.setAutoCommit(false);
            int cnt = 1;
            for (Object elems : data){
                preparedStatement.setObject(cnt++,elems);
            }
            res = preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
            throw new RuntimeException(e);
        } finally {
            JDBCUtils.release(connection,preparedStatement,null);
        }

        return res;
    }

    /**
     * 更新数据
     * @param sql
     * @param params
     * @return
     */
    public static int update(String sql, Object... params) {
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = null;
        int cnt = -1;
        try {
            preparedStatement = connection.prepareStatement(sql);
            connection.setAutoCommit(false);
            for(int i = 0; params != null && i < params.length; i++){
                preparedStatement.setObject(i+1, params[i]);
            }

            cnt = preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
            e.printStackTrace();
        } finally {
            JDBCUtils.release(connection,preparedStatement,null);
        }
        return cnt;
    }
}
