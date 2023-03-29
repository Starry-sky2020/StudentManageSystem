package com.njfu.edu.utils;

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
}
