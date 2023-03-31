package com.njfu.edu.dao.impl;

import com.njfu.edu.dao.UserDao;
import com.njfu.edu.pojo.User;
import com.njfu.edu.utils.CRUDUtils;

import java.io.IOException;
import java.util.List;

public class UserDaoImpl implements UserDao {

    private CRUDUtils crudUtils = new CRUDUtils();
    public void insertUser(String username, String password){

        String sql = "insert into lessontraining.user(username,password)" +
                "value(?,?)";

        crudUtils.insert(sql,username,password);
    }

    /**
     * 查询用户信息
     * 用于用户登录信息验证
     * @return
     * @throws IOException
     */

    public List<User> selectUserMessage() {

        String sql = "select * from lessontraining.user";
        List<User> userList = CRUDUtils.query(User.class, sql, null);

        return userList;
    }

    /**
     * 根据用户id删除用户
     * @param id
     */
    public void deleteUserById(String id){

        String sql = "delete from lessontraining.user where user_id = ?";
        crudUtils.delete(sql,id);
    }

}
