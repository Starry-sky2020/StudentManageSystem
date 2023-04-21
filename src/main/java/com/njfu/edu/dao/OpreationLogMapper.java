package com.njfu.edu.dao;

import com.njfu.edu.pojo.OperationLog;
import org.apache.ibatis.annotations.Param;

import java.sql.Connection;

public interface OpreationLogMapper {

    void insert(@Param("operationLog") OperationLog operationLog);
}
