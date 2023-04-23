package com.njfu.edu;

import com.njfu.edu.dao.StudentMapper;
import com.njfu.edu.pojo.Paging;
import com.njfu.edu.pojo.Student;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MybatisTest {

    @Test
    public void testselect(){
        Paging<Student> paging = new Paging<>();
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
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        List<Student> students = mapper.testselect();
//        System.out.println(students);
//        sqlSession.commit();

        SqlSession session = sqlSessionFactory.openSession(true);
        StudentMapper studentMapper = session.getMapper(StudentMapper.class);
        List<Student> students1 = studentMapper.testselect();
//        System.out.println(students1);
//        session.commit();

//        List<Student> testselect = studentMapper.testselect();
//        System.out.println(testselect);
//        List<Student> testselect1 = studentMapper.testselect();
//        System.out.println(testselect1);
//        sqlSession.commit();
//        sqlSession.close();
//        session.commit();
//        session.close();
    }
}
