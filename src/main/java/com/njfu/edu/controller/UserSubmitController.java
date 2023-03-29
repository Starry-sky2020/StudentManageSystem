package com.njfu.edu.controller;

import com.njfu.edu.pojo.SubmitResult;
import com.njfu.edu.service.impl.UserSubmitServiceImpl;

import java.io.IOException;
import java.util.Map;

public class UserSubmitController {

    private UserSubmitServiceImpl service = new UserSubmitServiceImpl();

    public SubmitResult userSubmit(Map<String, String> map) throws IOException {
        return service.userSubmit(map);
    }
}
