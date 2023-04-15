package com.njfu.edu.dao;

import com.njfu.edu.pojo.User;

import java.sql.Connection;
import java.util.List;

public interface UserDao {
    void insertUser(Connection connection,String username, String password);
    List<User> selectUserMessage(Connection connection);
    void deleteUserById(Connection connection,String id);
    Long selectUserIdByPhone(Connection connection, String phone);
}
