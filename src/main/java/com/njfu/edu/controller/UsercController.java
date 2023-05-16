package com.njfu.edu.controller;

import com.alibaba.fastjson2.JSON;
import com.njfu.edu.pojo.Ajax;
import com.njfu.edu.pojo.SubmitResult;
import com.njfu.edu.pojo.User;
import com.njfu.edu.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UsercController {

    @Autowired
    private UserService userService;

    @GetMapping("/manager/staff")
    public String selectUser(HttpServletRequest request) throws IOException {
        List<User> users = userService.selectAllUser();
        request.setAttribute("users",users);
        Ajax ajax = new Ajax();
        ajax.setListUser(users);
        String jsonString = JSON.toJSONString(ajax);

        return jsonString;
    }

    @PostMapping("/manager/deluser/{userId}")
    public void deleteUser(@PathVariable("userId") String userId) throws IOException {
        System.out.println(userId);
        userService.deleteUserById(userId);
    }

    @PostMapping("/usersubmit/{username}/{password}")
    public void userSumbit(@PathVariable("username") String username,
                           @PathVariable("password") String password,
                           HttpServletRequest request) throws IOException {

        Map map = new HashMap();
        map.put("username",username);
        map.put("password",password);

        SubmitResult submitResult = userService.userSubmit(map);
        request.setAttribute("userSubmit",submitResult);
    }
}
