package com.njfu.edu.dao;

import com.njfu.edu.pojo.Manager;
import org.apache.ibatis.annotations.Param;

import java.sql.Connection;
import java.util.List;

public interface ManagerMapper {

    List<Manager> selectManagerMessage();
    void insertManager(@Param("manager") Manager manager);
    Long selectManagerIdByPhone(@Param("phone") String phone);
}
