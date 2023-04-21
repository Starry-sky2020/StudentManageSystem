package com.njfu.edu.service.impl;

import com.njfu.edu.dao.CollegeMapper;
import com.njfu.edu.pojo.College;
import com.njfu.edu.service.CollegeService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class CollegeServiceImpl implements CollegeService {

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
    CollegeMapper mapper = sqlSession.getMapper(CollegeMapper.class);

    @Override
    public List<College> queryAllCollege() {
        List<College> colleges = mapper.queryData();
        return colleges;
    }
}
