package com.njfu.edu.service.impl;

import com.njfu.edu.dao.StudenClazzMapper;
import com.njfu.edu.pojo.StudentClass;
import com.njfu.edu.service.StudentClassService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class StudentClassServiceImpl implements StudentClassService {

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
    StudenClazzMapper mapper = sqlSession.getMapper(StudenClazzMapper.class);

    @Override
    public List<StudentClass> queryStudentClassById(int collegeId) {
//        Connection connection = JDBCUtils.getConnection();
//        List<StudentClass> studentClasses = studentClassDao.queryStudentClassById(connection, collegeId);
//        JDBCUtils.connRelease(connection);
        List<StudentClass> studentClasses = mapper.queryStudentClassById(collegeId);
        return studentClasses;
    }

    @Override
    public List<StudentClass> queryStudentClass() {
//        Connection connection = JDBCUtils.getConnection();
//        List<StudentClass> studentClasses = studentClassDao.queryStudentClass(connection);
//        JDBCUtils.connRelease(connection);
        List<StudentClass> studentClasses = mapper.queryStudentClass();
        return studentClasses;
    }
}
