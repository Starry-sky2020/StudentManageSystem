package com.njfu.edu.utils;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CRUDUtils<T> {

    public static<T> long selectItems(Connection connection,String sql, Object... params) throws SQLException {
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        long value = 0;

        try {
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; params != null && i < params.length; i++){
                preparedStatement.setObject(i+1,params[i]);
            }
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                value = (long) resultSet.getObject(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(null,preparedStatement,resultSet);
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
    public static <T> List<T> query(Connection connection,Class<T> tClass, String sql, Object... params){

        List<T> result = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; params != null && i < params.length; i++){
                preparedStatement.setObject(i+1,params[i]);
            }

            resultSet = preparedStatement.executeQuery();

            //取出集合结果中的列名
            ResultSetMetaData metaData = resultSet.getMetaData();
            List<String> list = new ArrayList<>();
            // 获取resultSet中的列数
            int cnt = metaData.getColumnCount();
            for (int i = 1; i <= cnt; i++){
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
                    if (columnLabel.equals("student_id") || columnLabel.equals("user_id"))
                        value = String.valueOf(value);

                    Field declaredField = tClass.getDeclaredField(columnLabel);
                    declaredField.setAccessible(true);
                    declaredField.set(t,value);
                }
                result.add(t);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(null,preparedStatement,resultSet);
        }

        return result;
    }

    /**
     * 插入数据
     * @param sql
     * @param data
     * @return
     */
    public static int insert(Connection connection,String sql, Object... data){
        int i = 0;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            int cnt = 1;
            for (Object elems : data){
                preparedStatement.setObject(cnt++,elems);
            }
            i = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.stmtRelease(preparedStatement);
        }

        return i;
    }

    /**
     * 删除数据
     * @param sql
     * @param data
     * @return
     */
    public static int delete(Connection connection,String sql, Object... data){
        int res = 0;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            int cnt = 1;
            for (Object elems : data){
                preparedStatement.setObject(cnt++,elems);
            }
            res = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.stmtRelease(preparedStatement);
        }

        return res;
    }

    /**
     * 更新数据
     * @param sql
     * @param params
     * @return
     */
    public static int update(Connection connection,String sql, Object... params) {
        PreparedStatement preparedStatement = null;
        int cnt = -1;
        try {
            preparedStatement = connection.prepareStatement(sql);
            for(int i = 0; params != null && i < params.length; i++){
                preparedStatement.setObject(i+1, params[i]);
            }
            cnt = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.stmtRelease(preparedStatement);
        }
        return cnt;
    }
}
