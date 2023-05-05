package com.njfu.edu.service.impl;

import com.njfu.edu.mapper.ManagerMapper;
import com.njfu.edu.mapper.OpreationLogMapper;
import com.njfu.edu.pojo.Manager;
import com.njfu.edu.pojo.SubmitResult;
import com.njfu.edu.service.ManagerService;
import com.njfu.edu.utils.SqlSessionUtil;
import com.njfu.edu.utils.Tools;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ManagerServiceImpl implements ManagerService {
    SqlSession sqlSession = SqlSessionUtil.getSqlSession();
    ManagerMapper mapper = sqlSession.getMapper(ManagerMapper.class);
    OpreationLogMapper logMapper = sqlSession.getMapper(OpreationLogMapper.class);

    @Override
    public SubmitResult createManger(Manager manager) throws IOException {
        SubmitResult submitResult = new SubmitResult();
        //正则验证用户名是否合法
        String regName = "^([\u4e00-\u9fa5a-zA-Z0-9]{2,12}$|([a-zA-Z]{2,16})$)";
        Pattern pattern = Pattern.compile(regName);
        Matcher matcher = pattern.matcher(manager.getManager_name());
        if (!matcher.find()) {
            submitResult.setResult(false);
            submitResult.setMessage("用户名不合法，请重新输入");
            submitResult.setCode(SubmitResult.ERROR_CODE_1);
            return submitResult;
        }

        //正则验证用户密码是否合法 手机号
        String regPassword = "^1(3[0-9]|5[0-3,5-9]|7[1-3,5-8]|8[0-9])\\d{8}$";
        pattern = Pattern.compile(regPassword);
        matcher = pattern.matcher(manager.getPassword());
        if (!matcher.find()) {
            submitResult.setResult(false);
            submitResult.setMessage("手机号码不合法，请重新输入");
            submitResult.setCode(SubmitResult.ERROR_CODE_2);
            return submitResult;
        }

        try {
            List<Manager> managerList = mapper.selectManagerMessage();
            sqlSession.commit();
            for (int i = 0; i < managerList.size(); i++) {
                if (manager.getManager_name().equals(managerList.get(i).getManager_name())) {
                    submitResult.setResult(false);
                    submitResult.setMessage("管理员已存在，请重新输入创建信息");
                    submitResult.setCode(SubmitResult.ERROR_CODE_3);
                    return submitResult;
                }
            }

            logMapper.insert(Tools.getOpreationLog("创建管理员", 1, "无"));
            sqlSession.commit();

            submitResult.setResult(true);
            submitResult.setMessage("创建管理员成功");
            submitResult.setCode(SubmitResult.ERROR_CODE_4);
            mapper.insertManager(manager);
            sqlSession.commit();

            return submitResult;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
        @Override
    public Long selectManagerIdByPhone(String phone) {
        Long aLong = mapper.selectManagerIdByPhone(phone);
        sqlSession.commit();
        return aLong;
    }
}