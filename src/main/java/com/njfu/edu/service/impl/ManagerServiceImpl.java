package com.njfu.edu.service.impl;

import com.njfu.edu.Main;
import com.njfu.edu.dao.ManagerDao;
import com.njfu.edu.dao.impl.LogDaoImpl;
import com.njfu.edu.dao.impl.ManagerDaoImpl;
import com.njfu.edu.pojo.Manager;
import com.njfu.edu.pojo.OperationLog;
import com.njfu.edu.pojo.SubmitResult;
import com.njfu.edu.service.ManagerService;
import com.njfu.edu.utils.JDBCUtils;
import com.njfu.edu.utils.Tools;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ManagerServiceImpl implements ManagerService {

    private ManagerDao managerDao = new ManagerDaoImpl();

    @Override
    public SubmitResult createManger(Manager manager) throws IOException {
        Connection connection = JDBCUtils.getConnection();
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

        boolean res = false;
        boolean autoCommit = false;
        try {
            autoCommit = connection.getAutoCommit();
            connection.setAutoCommit(false);

            List<Manager> managerList = managerDao.selectManagerMessage(connection);
            for (int i = 0; i < managerList.size(); i++){
                if (manager.getManager_name().equals(managerList.get(i).getManager_name())){
                    submitResult.setResult(false);
                    submitResult.setMessage("管理员已存在，请重新输入创建信息");
                    submitResult.setCode(SubmitResult.ERROR_CODE_3);
                    return submitResult;
                }
            }

            OperationLog operationLog = new OperationLog();
            operationLog.setOperationMsg("用户注册信息");
            operationLog.setDeleteFlag(1);
            operationLog.setUserId(Main.managerId);
            operationLog.setUpdateTime(Tools.getCurrentSystemDate());
            operationLog.setInfo("无");
            new LogDaoImpl().insert(connection,operationLog);

            submitResult.setResult(true);
            submitResult.setMessage("创建管理员成功");
            submitResult.setCode(SubmitResult.ERROR_CODE_4);
            managerDao.insertManager(connection,manager);

            res = true;
        } catch (SQLException | ParseException e) {
            e.printStackTrace();

            try {
                connection.rollback();
            } catch (SQLException ex) {
                e.printStackTrace();
            }
        } finally {

            if (res){
                try {
                   connection.commit();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            try {
                connection.setAutoCommit(autoCommit);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            JDBCUtils.connRelease(connection);
        }

        return submitResult;
    }
}