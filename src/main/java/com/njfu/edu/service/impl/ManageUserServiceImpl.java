package com.njfu.edu.service.impl;

import com.njfu.edu.dao.impl.ManagerDaoImpl;
import com.njfu.edu.dao.impl.UserDaoImpl;
import com.njfu.edu.pojo.Manager;
import com.njfu.edu.pojo.SubmitResult;
import com.njfu.edu.pojo.User;
import com.njfu.edu.service.ManageUserService;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ManageUserServiceImpl implements ManageUserService {

    private UserDaoImpl userDaoImpl = new UserDaoImpl();
    private ManagerDaoImpl managerDaoImpl = new ManagerDaoImpl();

    @Override
    public List<User> selectAllUser() throws IOException {
        return userDaoImpl.selectUserMessage();
    }

    @Override
    public void deleteUserById(String id) throws IOException {
       userDaoImpl.deleteUserById(id);
    }

    @Override
    public SubmitResult createManger(Manager manager) throws IOException {
        SubmitResult submitResult = new SubmitResult();
        //正则验证用户名是否合法
        String regName = "^([\u4e00-\u9fa5a-zA-Z0-9]{2,12}$|([a-zA-Z]{2,16})$)";
        Pattern pattern = Pattern.compile(regName);
        Matcher matcher = pattern.matcher(manager.getManager_name());
        if (!matcher.find()){
            submitResult.setResult(false);
            submitResult.setMessage("用户名不合法，请重新输入");
            submitResult.setCode(SubmitResult.ERROR_CODE_1);
            return submitResult;
        }

        //正则验证用户密码是否合法
        String regPassword = "^(?=.*[0-9])|(?=.*[a-z])|(?=.*[A-Z])|(?=.*[@#$%^&+=])|(?=\\S+$).{4,20}$";
        pattern = Pattern.compile(regPassword);
        matcher = pattern.matcher(manager.getPassword());
        if (!matcher.find()){
            submitResult.setResult(false);
            submitResult.setMessage("密码设置不合法，请重新输入");
            submitResult.setCode(SubmitResult.ERROR_CODE_2);
            return submitResult;
        }

        List<Manager> managerList = managerDaoImpl.selectManagerMessage();
        for (int i = 0; i < managerList.size(); i++){
            if (manager.getManager_name().equals(managerList.get(i).getManager_name())){
                submitResult.setResult(false);
                submitResult.setMessage("管理员已存在，请重新输入创建信息");
                submitResult.setCode(SubmitResult.ERROR_CODE_3);
                return submitResult;
            }
        }

        submitResult.setResult(true);
        submitResult.setMessage("创建管理员成功");
        submitResult.setCode(SubmitResult.ERROR_CODE_4);
        managerDaoImpl.insertManager(manager);

        return submitResult;
    }
}
