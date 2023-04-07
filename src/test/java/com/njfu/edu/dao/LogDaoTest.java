package com.njfu.edu.dao;


import com.njfu.edu.dao.impl.LogDaoImpl;
import com.njfu.edu.pojo.OperationLog;
import com.njfu.edu.utils.JDBCUtils;
import com.njfu.edu.utils.Tools;
import org.junit.Test;

import java.sql.Connection;
import java.text.ParseException;

public class LogDaoTest {

    @Test
    public void testInsert() throws ParseException {
        Connection connection = JDBCUtils.getConnection();
        LogDaoImpl logDao = new LogDaoImpl();
        OperationLog operationLog = new OperationLog();
        operationLog.setOperationMsg("更新学生信息");
        operationLog.setDeleteFlag(1);
        //管理员和用户都具有管理学生的功能，判断执行对象是谁
        operationLog.setUserId(222);

        operationLog.setInfo("无");
        operationLog.setUpdateTime(Tools.getCurrentSystemDate());
        logDao.insert(connection,operationLog);
    }
}
