package com.njfu.edu.service.impl;

import com.njfu.edu.dao.OpreationLogMapper;
import com.njfu.edu.dao.UserMapper;
import com.njfu.edu.pojo.SubmitResult;
import com.njfu.edu.pojo.User;
import com.njfu.edu.service.UserService;
import com.njfu.edu.utils.JDBCUtils;
import com.njfu.edu.utils.Tools;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserServiceImpl implements UserService {

    private SubmitResult submitResult = new SubmitResult();

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
    UserMapper mapper = sqlSession.getMapper(UserMapper.class);
    OpreationLogMapper logMapper = sqlSession.getMapper(OpreationLogMapper.class);

    @Override
    public List<User> selectAllUser() throws IOException {
        Connection connection = JDBCUtils.getConnection();
        List<User> users = mapper.selectUserMessage();
//        return userDao.selectUserMessage(connection);
        return users;
    }

    @Override
    public void deleteUserById(String id) throws IOException {
//        Connection connection = JDBCUtils.getConnection();
        mapper.deleteUserById("id");
//        userDao.deleteUserById(connection,id);
    }

    /**
     * 用户注册服务
     * @param map
     */
    @Override
    public SubmitResult userSubmit(Map<String, String> map) throws IOException {
        Connection connection = JDBCUtils.getConnection();


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

        boolean autoCommit = false;
        boolean res = false;

        try {
            autoCommit = connection.getAutoCommit();
            connection.setAutoCommit(false);
//            List<User> userList = userDao.selectUserMessage(connection);
            List<User> userList = mapper.selectUserMessage();
            System.out.println(userList);
            //用户名重复检测
            for (int i = 0; i < userList.size(); i++){
                if (map.get("username").equals(userList.get(i).getUsername())){
                    submitResult.setResult(false);
                    submitResult.setMessage("用户名已存在，请重新输入注册信息");
                    submitResult.setCode(SubmitResult.ERROR_CODE_3);
                    return submitResult;
                }
            }

//            userDao.insertUser(connection,map.get("username"),map.get("password"));
            mapper.insertUser(map.get("username"),map.get("password"));
            sqlSession.commit();
//            new LogDaoImpl().insert(connection,
//                    Tools.getOpreationLog("用户注册信息",1,"无"));
            logMapper.insert(Tools.getOpreationLog("用户注册信息",1,"无"));
            sqlSession.commit();
            submitResult.setResult(true);
            submitResult.setMessage("用户注册成功");
            submitResult.setCode(SubmitResult.ERROR_CODE_4);

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
                    throw new RuntimeException(e);
                }
            }

            try {
                connection.setAutoCommit(autoCommit);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            JDBCUtils.connRelease(connection);
        }

        return submitResult;
    }

    @Override
    public Long selectUserIdByPhone(String phone) {
        Connection connection = JDBCUtils.getConnection();
//        Long aLong = userDao.selectUserIdByPhone(connection, phone);
        Long aLong = mapper.selectUserIdByPhone(phone);
        return aLong;
    }


}
