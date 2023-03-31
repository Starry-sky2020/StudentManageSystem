package com.njfu.edu.dao;

import com.njfu.edu.pojo.Manager;

import java.util.List;

public interface ManagerDao {

    List<Manager> selectManagerMessage();
    void insertManager(Manager manager);
}
