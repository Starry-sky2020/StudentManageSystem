package com.njfu.edu;

import com.njfu.edu.dao.StudentMapper;
import com.njfu.edu.dao.UserMapper;
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
        paging.setPageNum(3);
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
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        UserMapper mapper1 = sqlSession.getMapper(UserMapper.class);

        Long aLong = mapper1.selectUserIdByPhone("13851701111");
        System.out.println(aLong);


    }
}
