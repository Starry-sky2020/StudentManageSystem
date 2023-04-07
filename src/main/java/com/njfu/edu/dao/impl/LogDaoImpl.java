package com.njfu.edu.dao.impl;

import com.njfu.edu.dao.LogDao;
import com.njfu.edu.pojo.OperationLog;
import com.njfu.edu.utils.CRUDUtils;

import java.sql.Connection;

public class LogDaoImpl implements LogDao {
    @Override
    public void insert(Connection connection,OperationLog operationLog) {

        String sql = "insert into lessontraining.operationLog(operationMsg,deleteFlag,userId,info,updateTime) " +
                "value(?,?,?,?,?)";
        CRUDUtils.insert(connection,sql,
                operationLog.getOperationMsg(),
                operationLog.getDeleteFlag(),
                operationLog.getUserId(),
                operationLog.getInfo(),
                operationLog.getUpdateTime());
    }
}
