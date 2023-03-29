package com.njfu.edu.controller;

import com.njfu.edu.service.impl.CheckPersonServiceImpl;

import java.io.IOException;
import java.util.Map;

public class CheckPersonController {

    private CheckPersonServiceImpl checkPersonServiceImpl = new CheckPersonServiceImpl();

    public Boolean UserLoginView(Map<String,String> map) throws IOException {
        return checkPersonServiceImpl.UserLoginView(map);
    }

    public Boolean ManagerLoginView(Map<String,String> map) throws IOException {
        return checkPersonServiceImpl.ManagerLoginView(map);
    }
}
