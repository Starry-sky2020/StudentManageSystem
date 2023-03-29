package com.njfu.edu;

import com.njfu.edu.controller.IntoSystemController;

import java.io.IOException;


public class Main {
    public static void main( String[] args ) throws IOException {
        IntoSystemController intoSystemController = new IntoSystemController();
        intoSystemController.MainView();

    }
}
