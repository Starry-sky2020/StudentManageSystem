package com.njfu.edu.dao;

import com.njfu.edu.pojo.User;

import java.util.List;

public interface UserDao {
    void insertUser(String username, String password);
    List<User> selectUserMessage();
    void deleteUserById(String id);
}
