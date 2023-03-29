package com.njfu.edu.service;

import com.njfu.edu.pojo.Manager;
import com.njfu.edu.pojo.SubmitResult;
import com.njfu.edu.pojo.User;

import java.io.IOException;
import java.util.List;

public interface ManageUserService {

    /**
     * 管理员管理学生信息
     * 与用户功能重合
     */
//    void ToUser() throws IOException;

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
     * 创建管理账号
     * @param manager
     */
    SubmitResult createManger(Manager manager) throws IOException;
}
