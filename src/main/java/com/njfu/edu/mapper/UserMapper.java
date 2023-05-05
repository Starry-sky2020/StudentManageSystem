package com.njfu.edu.mapper;

import com.njfu.edu.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    void insertUser(@Param("username") String username, @Param("password") String password);
    List<User> selectUserMessage();
    void deleteUserById(@Param("id") String id);
    Long selectUserIdByPhone(@Param("phone") String phone);
}
