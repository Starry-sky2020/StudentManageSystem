package com.njfu.edu.service.impl;

import com.njfu.edu.dao.ManagerDao;
import com.njfu.edu.dao.UserDao;
import com.njfu.edu.pojo.Manager;
import com.njfu.edu.pojo.User;
import com.njfu.edu.service.CheckPersonService;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class CheckPersonServiceImpl implements CheckPersonService {

    private ManagerDao managerDao = new ManagerDao();
    private UserDao userDao = new UserDao();
    /**
     * 用户登录信息验证
     * @throws IOException
     */
    public Boolean UserLoginView(Map<String,String> map) throws IOException {
        List<User> userList = userDao.selectUserMessage();
        for (int i = 0; i < userList.size(); i++){
            if (userList.get(i).getUsername().equals(map.get("username"))){
                if (userList.get(i).getPassword().equals(map.get("password"))){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 管理员登录信息验证
     * @throws IOException
     */
    public Boolean ManagerLoginView(Map<String,String> map) throws IOException {
        List<Manager> managerList = managerDao.selectManagerMessage();
        for (int i = 0; i < managerList.size(); i++){
            if (managerList.get(i).getManager_name().equals(map.get("managername"))){
                if (managerList.get(i).getPassword().equals(map.get("password"))){
                    return true;
                }
            }
        }
        return false;
    }

}
