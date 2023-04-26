package com.njfu.edu.service.impl;

import com.njfu.edu.dao.OpreationLogMapper;
import com.njfu.edu.dao.UserMapper;
import com.njfu.edu.pojo.SubmitResult;
import com.njfu.edu.pojo.User;
import com.njfu.edu.service.UserService;
import com.njfu.edu.utils.SqlSessionUtil;
import com.njfu.edu.utils.Tools;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserServiceImpl implements UserService {

    private SubmitResult submitResult = new SubmitResult();

    SqlSession sqlSession = SqlSessionUtil.getSqlSession();
    UserMapper mapper = sqlSession.getMapper(UserMapper.class);
    OpreationLogMapper logMapper = sqlSession.getMapper(OpreationLogMapper.class);

    @Override
    public List<User> selectAllUser() throws IOException {
        List<User> users = mapper.selectUserMessage();
        sqlSession.commit();sqlSession.close();
        return users;
    }

    @Override
    public void deleteUserById(String id) throws IOException {
        mapper.deleteUserById("id");
        sqlSession.commit();sqlSession.close();
    }

    /**
     * 用户注册服务
     * @param map
     */
    @Override
    public SubmitResult userSubmit(Map<String, String> map) throws IOException {
        //正则验证用户名是否合法
        String regName = "^([\u4e00-\u9fa5a-zA-Z0-9]{2,12}$|([a-zA-Z]{2,16})$)";
        Pattern pattern = Pattern.compile(regName);
        Matcher matcher = pattern.matcher(map.get("username"));
        if (!matcher.find()){
            submitResult.setResult(false);
            submitResult.setMessage("用户名不合法，请重新输入");
            submitResult.setCode(SubmitResult.ERROR_CODE_1);
            return submitResult;
        }

        //正则验证用户密码是否合法
        String regPassword = "^(?=.*[0-9])|(?=.*[a-z])|(?=.*[A-Z])|(?=.*[@#$%^&+=])|(?=\\S+$).{4,20}$";
        pattern = Pattern.compile(regPassword);
        matcher = pattern.matcher(map.get("password"));
        if (!matcher.find()){
            submitResult.setResult(false);
            submitResult.setMessage("密码设置不合法，请重新输入");
            submitResult.setCode(SubmitResult.ERROR_CODE_2);
            return submitResult;
        }

        try {
            List<User> userList = mapper.selectUserMessage();
            sqlSession.commit();
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
            sqlSession.commit();
            logMapper.insert(Tools.getOpreationLog("用户注册信息",1,"无"));
            sqlSession.commit();
            submitResult.setResult(true);
            submitResult.setMessage("用户注册成功");
            submitResult.setCode(SubmitResult.ERROR_CODE_4);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        } finally {
            sqlSession.close();
        }

        return submitResult;
    }

    @Override
    public Long selectUserIdByPhone(String phone) {
        Long aLong = mapper.selectUserIdByPhone(phone);
        sqlSession.commit();sqlSession.close();
        return aLong;
    }


}
