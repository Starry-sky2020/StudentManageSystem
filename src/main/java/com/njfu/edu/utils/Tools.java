package com.njfu.edu.utils;

import com.njfu.edu.controller.CheckPersonController;
import com.njfu.edu.pojo.OperationLog;
import lombok.Data;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Tools {

    /**
     * 检查是否为数字类型
     * @param str
     * @return
     */
    public static boolean checkInt(String str){
        boolean flg = true;
        try {
            Integer.parseInt(str);
        } catch (Exception e){
            flg = false;
        }
        return flg;
    }

    /**
     * 检查是否字符串类型
     * @param str
     * @return
     */
    public static boolean checkString(String str){
        if (str instanceof String)
            return true;
        return false;
    }


    public static Timestamp getCurrentSystemDate() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(System.currentTimeMillis());
        return Timestamp.valueOf(format);
    }

    /**
     * 记录系统日志
     * @param opMsg
     * @param deleteFlag
     * @param info
     * @return
     * @throws ParseException
     */
    public static OperationLog getOpreationLog(String opMsg, int deleteFlag,String info) throws ParseException {
        OperationLog operationLog = new OperationLog();
        operationLog.setOperationMsg(opMsg);
        operationLog.setDeleteFlag(deleteFlag);
        operationLog.setUpdateTime(getCurrentSystemDate());
        operationLog.setInfo(info);

        if (CheckPersonController.indentity){
            operationLog.setIdentity("管理员");
            operationLog.setManagerId(CheckPersonController.id);
        } else {
            operationLog.setIdentity("普通员工");
            operationLog.setUserId(CheckPersonController.id);
        }
        return operationLog;
    }
}
