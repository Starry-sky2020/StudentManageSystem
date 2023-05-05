package com.njfu.edu.mapper;

import com.njfu.edu.pojo.OperationLog;
import org.apache.ibatis.annotations.Param;

public interface OpreationLogMapper {

    void insert(@Param("operationLog") OperationLog operationLog);
}
