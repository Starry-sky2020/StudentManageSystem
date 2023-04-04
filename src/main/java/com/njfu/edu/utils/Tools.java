package com.njfu.edu.utils;

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
}
