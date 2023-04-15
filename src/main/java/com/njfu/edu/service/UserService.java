package com.njfu.edu.service;

import com.njfu.edu.pojo.Manager;
import com.njfu.edu.pojo.SubmitResult;
import com.njfu.edu.pojo.User;
import jakarta.servlet.http.HttpServlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface UserService {


    /**
     * 查询所有用户
     * @return
     */
    List<User> selectAllUser() throws IOException;

    /**
     * 根据员工id删除用户
     * @param id
     */
    void deleteUserById(String id) throws IOException;

    /**
     * 用户注册
     * @param map
     * @return
     * @throws IOException
     */
    SubmitResult userSubmit(Map<String, String> map) throws IOException;

    /**
     * 获取用户id
     * @param phone
     * @return
     */
    Long selectUserIdByPhone(String phone);
}
