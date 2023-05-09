package com.njfu.edu;

import com.njfu.edu.service.CheckPersonService;
import com.njfu.edu.service.impl.CheckPersonServiceImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class SpringTest {
    @Test
    public void aop() throws SQLException {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        CheckPersonService checkPersonService = classPathXmlApplicationContext.getBean("checkPersonService", CheckPersonService.class);

        checkPersonService.test(1);
    }

    @Test
    public void jdbcTemplate(){
        //启动IoC容器
        ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
        //获取IoC容器中JdbcTemplate实例
        JdbcTemplate jdbcTemplate=(JdbcTemplate) ctx.getBean("jdbcTemplate");
        String sql="select * from lessontraining.student";
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
        System.out.println(maps);
    }
}
