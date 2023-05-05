package com.njfu.edu.mapper;

import com.njfu.edu.pojo.Manager;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ManagerMapper {

    List<Manager> selectManagerMessage();
    void insertManager(@Param("manager") Manager manager);
    Long selectManagerIdByPhone(@Param("phone") String phone);
}
