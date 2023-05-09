package com.njfu.edu;

import com.njfu.edu.service.CheckPersonService;
import com.njfu.edu.service.impl.CheckPersonServiceImpl;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;

public class SpringTest {
    @Test
    public void test01() throws SQLException {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        CheckPersonService checkPersonService = classPathXmlApplicationContext.getBean("checkPersonService", CheckPersonService.class);

        checkPersonService.test(1);
    }

    @Test
    public void AopTest(){

    }
}
