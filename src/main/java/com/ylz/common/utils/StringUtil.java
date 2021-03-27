package com.ylz.common.utils;

import java.util.UUID;

/**
 * @author ylz
 * @Description
 * @date 2021-03-27-14:19
 */
public class StringUtil {
    /**
     * 判断是否是空字符串null和""
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str){
        if (str != null && !str.equals("")) {
            return false;
        }
        return true;
    }

    /**
     * 判断两个字符串是否相等 如果都为null则判断为相等,一个为null另一个not null则判断不相等 否则如果s1=s2则相等
     *
     * @param s1
     * @param s2
     * @return
     */
    public static boolean equals(String s1, String s2) {
        if (StringUtil.isEmpty(s1) && StringUtil.isEmpty(s2)) {
            return true;
        } else if (!StringUtil.isEmpty(s1) && !StringUtil.isEmpty(s2)) {
            return s1.equals(s2);
        }
        return false;
    }

    /**
     * 生成uuid
     * @return
     */
    public static String getUUID(){
        return UUID.randomUUID().toString();
    }

}
