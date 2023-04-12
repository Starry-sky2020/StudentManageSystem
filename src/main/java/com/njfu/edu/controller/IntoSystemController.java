package com.njfu.edu.controller;

import com.njfu.edu.View.impl.IntoSystem;

import java.io.IOException;

public class IntoSystemController {
    private IntoSystem intoSystem = new IntoSystem();

    public void MainView() throws IOException {
        intoSystem.IntoSystemView();
    }
}
