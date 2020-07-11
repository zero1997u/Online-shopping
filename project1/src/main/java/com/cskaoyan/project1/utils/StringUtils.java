package com.cskaoyan.project1.utils;

public class StringUtils {
    public static boolean isEmpty(String s){
        if(s==null||"".equals(s.trim())){
            return true;
        }
        return false;
    }
}
