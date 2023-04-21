package com.njfu.edu.service.impl;

import com.njfu.edu.dao.ManagerMapper;
import com.njfu.edu.dao.UserMapper;
import com.njfu.edu.pojo.Manager;
import com.njfu.edu.pojo.User;
import com.njfu.edu.service.CheckPersonService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class CheckPersonServiceImpl implements CheckPersonService {
    String resource = "mybatis-config.xml";
    InputStream inputStream;

    {
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    SqlSession sqlSession = sqlSessionFactory.openSession();
    UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
    ManagerMapper managerMapper = sqlSession.getMapper(ManagerMapper.class);

    /**
     * 用户登录信息验证
     * @throws IOException
     */
    public Boolean UserLoginView(Map<String, String> map) throws IOException {
        List<User> userList = userMapper.selectUserMessage();
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
        List<Manager> managerList = managerMapper.selectManagerMessage();
        for (int i = 0; i < managerList.size(); i++)
            if (managerList.get(i).getManager_name().equals(map.get("managername")))
                if (managerList.get(i).getPassword().equals(map.get("password")))
                    return true;
        return false;
    }

}
