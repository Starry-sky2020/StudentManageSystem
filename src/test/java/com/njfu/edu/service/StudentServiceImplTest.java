package com.njfu.edu.service;

import com.njfu.edu.pojo.Student;
import com.njfu.edu.pojo.User;
import com.njfu.edu.service.impl.StudentServiceImpl;
import com.njfu.edu.service.impl.UserServiceImpl;
import com.njfu.edu.utils.Tools;
import org.junit.Test;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public class StudentServiceImplTest {

    @Test
    public void testUpdate() throws ParseException, IOException {
        UserServiceImpl userService = new UserServiceImpl();
        List<User> users = userService.selectAllUser();
        System.out.println(users);
    }
}
