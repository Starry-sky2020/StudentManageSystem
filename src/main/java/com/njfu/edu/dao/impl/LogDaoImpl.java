package com.njfu.edu.dao.impl;

import com.njfu.edu.dao.LogDao;
import com.njfu.edu.pojo.OperationLog;
import com.njfu.edu.utils.CRUDUtils;

public class LogDaoImpl implements LogDao {
    @Override
    public void insert(OperationLog operationLog) {

        String sql = "insert into lessontraining.operationLog()" +
                "value(?,?,?,?,?)";
        CRUDUtils.insert(sql,
                operationLog.getOperationMsg(),
                operationLog.getDeleteFlag(),
                operationLog.getUserId(),
                operationLog.getInfo(),
                operationLog.getUpdateTime());
    }
}
