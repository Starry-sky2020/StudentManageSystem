package com.njfu.edu.service.impl;

import com.njfu.edu.mapper.ManagerMapper;
import com.njfu.edu.mapper.UserMapper;
import com.njfu.edu.pojo.Manager;
import com.njfu.edu.pojo.User;
import com.njfu.edu.service.CheckPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class CheckPersonServiceImpl implements CheckPersonService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ManagerMapper managerMapper;
    /**
     * 用户登录信息验证
     * @throws IOException
     */
    public Boolean UserLoginView(Map<String, String> map) throws IOException {
        List<User> userList = userMapper.selectUserMessage();
        for (int i = 0; i < userList.size(); i++)
            if (userList.get(i).getUsername().equals(map.get("username"))){
                if (userList.get(i).getPassword().equals(map.get("password"))){
                    return true;
                }
            }
        return false;
    }

    public void test(int i){

        System.out.println("hello,world");
    }
    /**
     * 管理员登录信息验证
     * @throws IOException
     */
    public Boolean ManagerLoginView(Map<String,String> map) throws IOException {
        List<Manager> managerList = managerMapper.selectManagerMessage();
        for (int i = 0; i < managerList.size(); i++)
            if (managerList.get(i).getManager_name().equals(map.get("managername")))
                if (managerList.get(i).getPassword().equals(map.get("password")))
                    return true;
        return false;
    }

}
