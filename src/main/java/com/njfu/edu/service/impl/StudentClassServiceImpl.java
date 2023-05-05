package com.njfu.edu.service.impl;

import com.njfu.edu.mapper.StudenClazzMapper;
import com.njfu.edu.pojo.StudentClass;
import com.njfu.edu.service.StudentClassService;
import com.njfu.edu.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StudentClassServiceImpl implements StudentClassService {

    SqlSession sqlSession = SqlSessionUtil.getSqlSession();
    StudenClazzMapper mapper = sqlSession.getMapper(StudenClazzMapper.class);

    @Override
    public List<StudentClass> queryStudentClassById(int id) {
        List<StudentClass> studentClasses = mapper.queryStudentClassById(id);
        sqlSession.commit();
        return studentClasses;
    }

    @Override
    public List<StudentClass> queryStudentClass() {
        List<StudentClass> studentClasses = mapper.queryStudentClass();
        sqlSession.commit();
        return studentClasses;
    }
}
