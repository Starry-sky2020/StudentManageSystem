package com.njfu.edu.service.impl;

import com.njfu.edu.mapper.OpreationLogMapper;
import com.njfu.edu.mapper.UserMapper;
import com.njfu.edu.pojo.SubmitResult;
import com.njfu.edu.pojo.User;
import com.njfu.edu.service.UserService;
import com.njfu.edu.utils.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private SubmitResult submitResult;
    @Autowired
    private OpreationLogMapper logMapper;
    @Autowired
    private UserMapper mapper;


    @Override
    public List<User> selectAllUser() throws IOException {
        List<User> users = mapper.selectUserMessage();
        return users;
    }

    @Override
    public void deleteUserById(String id) throws IOException {
        mapper.deleteUserById(id);
    }

    /**
     * 用户注册服务
     * @param map
     */
    @Override
    public SubmitResult userSubmit(Map<String, String> map) throws IOException {
        try {
            List<User> userList = mapper.selectUserMessage();
            //用户名重复检测
            for (int i = 0; i < userList.size(); i++){
                if (map.get("username").equals(userList.get(i).getUsername())){
                    submitResult.setResult(false);
                    submitResult.setMessage("用户名已存在，请重新输入注册信息");
                    submitResult.setCode(SubmitResult.ERROR_CODE_3);
                    return submitResult;
                }
            }

            mapper.insertUser(map.get("username"),map.get("password"));
            logMapper.insert(Tools.getOpreationLog("用户注册信息",1,"无"));
            submitResult.setResult(true);
            submitResult.setMessage("用户注册成功");
            submitResult.setCode(SubmitResult.ERROR_CODE_4);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        return submitResult;
    }

    @Override
    public Long selectUserIdByPhone(String phone) {
        Long aLong = mapper.selectUserIdByPhone(phone);
        return aLong;
    }


}
