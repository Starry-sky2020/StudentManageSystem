package com.njfu.edu.service.impl;

import com.njfu.edu.dao.impl.ManagerDaoImpl;
import com.njfu.edu.dao.impl.UserDaoImpl;
import com.njfu.edu.pojo.Manager;
import com.njfu.edu.pojo.User;
import com.njfu.edu.service.CheckPersonService;
import com.njfu.edu.utils.JDBCUtils;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

public class CheckPersonServiceImpl implements CheckPersonService {

    private ManagerDaoImpl managerDaoImpl = new ManagerDaoImpl();
    private UserDaoImpl userDaoImpl = new UserDaoImpl();
    /**
     * 用户登录信息验证
     * @throws IOException
     */
    public Boolean UserLoginView(Map<String, String> map) throws IOException {
        Connection connection = JDBCUtils.getConnection();
        List<User> userList = userDaoImpl.selectUserMessage(connection);
        for (int i = 0; i < userList.size(); i++)
            if (userList.get(i).getUsername().equals(map.get("username"))){
                if (userList.get(i).getPassword().equals(map.get("password")))
                    return true;
            }
        return false;
    }

    /**
     * 管理员登录信息验证
     * @throws IOException
     */
    public Boolean ManagerLoginView(Map<String,String> map) throws IOException {
        Connection connection = JDBCUtils.getConnection();
        List<Manager> managerList = managerDaoImpl.selectManagerMessage(connection);
        for (int i = 0; i < managerList.size(); i++)
            if (managerList.get(i).getManager_name().equals(map.get("managername")))
                if (managerList.get(i).getPassword().equals(map.get("password")))
                    return true;
        return false;
    }

}
