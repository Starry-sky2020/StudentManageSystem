package com.njfu.edu.dao.impl;

import com.njfu.edu.dao.UserDao;
import com.njfu.edu.pojo.User;
import com.njfu.edu.utils.CRUDUtils;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

public class UserDaoImpl implements UserDao {

    private CRUDUtils crudUtils = new CRUDUtils();
    public void insertUser(Connection connection,String username, String password){

        String sql = "insert into lessontraining.user(username,password)" +
                "value(?,?)";
        crudUtils.insert(connection,sql,username,password);
    }

    /**
     * 查询用户信息
     * 用于用户登录信息验证
     * @return
     * @throws IOException
     */

    public List<User> selectUserMessage(Connection connection) {

        String sql = "select * from lessontraining.user";
        System.out.println("hhhhhhhhhh");
        List<User> userList = CRUDUtils.query(connection,User.class, sql, null);

        return userList;
    }

    /**
     * 根据用户id删除用户
     * @param id
     */
    public void deleteUserById(Connection connection,String id){

        String sql = "delete from lessontraining.user where user_id = ?";
        crudUtils.delete(connection,sql,id);
    }

}
