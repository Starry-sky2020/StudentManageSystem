package com.njfu.edu.controller;

import com.njfu.edu.View.impl.IntoSystem;
import com.njfu.edu.utils.JDBCUtils;

import java.io.IOException;
import java.sql.Connection;

public class IntoSystemController {
    private IntoSystem intoSystem = new IntoSystem();

    public void MainView() throws IOException {
        intoSystem.IntoSystemView();
    }
}
