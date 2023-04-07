package com.njfu.edu.controller;

import com.njfu.edu.service.impl.CheckPersonServiceImpl;
import com.njfu.edu.utils.JDBCUtils;
import jdk.nashorn.internal.scripts.JD;

import java.io.IOException;
import java.sql.Connection;
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
