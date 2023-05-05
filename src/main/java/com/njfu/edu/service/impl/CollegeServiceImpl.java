package com.njfu.edu.service.impl;

import com.njfu.edu.mapper.CollegeMapper;
import com.njfu.edu.pojo.College;
import com.njfu.edu.service.CollegeService;
import com.njfu.edu.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollegeServiceImpl implements CollegeService {
    SqlSession sqlSession = SqlSessionUtil.getSqlSession();
    CollegeMapper mapper = sqlSession.getMapper(CollegeMapper.class);

    @Override
    public List<College> queryAllCollege() {
        List<College> colleges = mapper.queryData();
        sqlSession.commit();
        return colleges;
    }
}
