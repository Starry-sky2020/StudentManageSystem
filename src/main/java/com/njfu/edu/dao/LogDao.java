package com.njfu.edu.dao;

import com.njfu.edu.pojo.OperationLog;

import java.sql.Connection;

public interface LogDao {

    void insert(Connection connection,OperationLog operationLog);
}
