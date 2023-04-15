package com.njfu.edu.dao.impl;

import com.njfu.edu.dao.UserDao;
import com.njfu.edu.pojo.User;
import com.njfu.edu.utils.CRUDUtils;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

public class UserDaoImpl implements UserDao {

    public void insertUser(Connection connection,String username, String password){
        String sql = "insert into lessontraining.user(username,password)" +
                "value(?,?)";
        CRUDUtils.insert(connection,sql,username,password);
    }

    /**
     * 查询用户信息
     * 用于用户登录信息验证
     * @return
     * @throws IOException
     */
    public List<User> selectUserMessage(Connection connection) {
        String sql = "select * from lessontraining.user";
        List<User> userList = CRUDUtils.query(connection,User.class, sql, null);
        return userList;
    }

    /**
     * 根据用户id删除用户
     * @param id
     */
    public void deleteUserById(Connection connection,String id){
        String sql = "delete from lessontraining.user where user_id = ?";
        CRUDUtils.delete(connection,sql,id);
    }

    /**
     * 更具用户手机号码获取用户id
     * 记录系统运行日志时使用
     * @param connection
     * @param phone
     * @return
     */
    @Override
    public Long selectUserIdByPhone(Connection connection, String phone) {
        String sql = "select user_id from lessontraining.user where password = ?";
        List<User> query = CRUDUtils.query(connection, User.class, sql, null);
        return query.get(0).getStudentId();
    }

}
