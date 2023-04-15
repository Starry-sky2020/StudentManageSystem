package com.njfu.edu.dao;

import com.njfu.edu.pojo.Manager;

import java.sql.Connection;
import java.util.List;

public interface ManagerDao {

    List<Manager> selectManagerMessage(Connection connection);
    void insertManager(Connection connection,Manager manager);
    Long selectManagerIdByPhone(Connection connection, String phone);
}
