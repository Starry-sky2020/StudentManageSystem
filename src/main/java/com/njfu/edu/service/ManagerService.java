package com.njfu.edu.service;

import com.njfu.edu.pojo.Manager;
import com.njfu.edu.pojo.SubmitResult;

import java.io.IOException;
import java.sql.Connection;

public interface ManagerService {
    SubmitResult createManger(Manager manager) throws IOException;
}
