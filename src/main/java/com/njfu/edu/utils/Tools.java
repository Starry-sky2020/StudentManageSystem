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

    /**
     * 比较String类型
     * @param str1
     * @param str2
     * @return
     */
    public static boolean compareString(String str1, String str2){
        if (str1.length() < str2.length())
            return false;
        else if (str1.length() == str2.length()){
            for (int i = 0; i < str1.length(); i++){
                if (str2.indexOf(i) - '0' > str1.indexOf(i)){
                    return false;
                }
            }
        } else {
            if (str2.compareTo(str1) > 0)
                return false;
        }
        return true;
    }

    public static Timestamp getCurrentSystemDate() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(System.currentTimeMillis());
        return Timestamp.valueOf(format);
    }
}
