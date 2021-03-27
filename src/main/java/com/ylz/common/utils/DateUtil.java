package com.ylz.common.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author ylz
 * @Description 时间工具类
 * @date 2021-03-23-22:09
 */
public class DateUtil {

    public final static SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static SimpleDateFormat sdf_date_format = new SimpleDateFormat("YYYY-MM-dd");

    /**
     * 获取当前时间的yyyy-MM-dd HH:mm:ss格式
     * @return
     */
    public static String getTime(){
        return sdfTime.format(new Date());
    }

    /**
     * g格式化日期
     * @param date
     * @return
     */
    public static Date formatDate(String date){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try{
            return dateFormat.parse(date);
        }catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 日期比较
     * @param s
     * @param e
     * @return
     */
    public static boolean compareDate(String s , String e ){
        if (formatDate(s) == null || formatDate(e) == null){
            return false;
        }
        return s.compareTo(e)>0;
    }

    /**
     * 获取当前时间的后i天
     * @param i
     * @return
     */
    public static String getAddDay(int i){
        String currentTime = DateUtil.getTime();
        GregorianCalendar gregorianCalendar = new GregorianCalendar(
                Integer.parseInt(currentTime.substring(0, 4)),
                Integer.parseInt(currentTime.substring(5, 7)) - 1,
                Integer.parseInt(currentTime.substring(8, 10)));
        gregorianCalendar.add(GregorianCalendar.DATE,i);
        return sdf_date_format.format(gregorianCalendar.getTime());

    }
    /**
     * 获取当前时间的后i天
     * 精确到秒
     * @param i
     * @return
     */
    public static String getAddDayTime(int i){
        Date date = new Date(System.currentTimeMillis()+i*24*60*60*1000);
        return sdfTime.format(date);
    }

    /**
     * 获取当前时间的+多少秒
     * 精确到秒
     * @param i
     * @return
     */
    public static String getAddDaySecond(int i){
        Date date = new Date(System.currentTimeMillis()+i*1000);
        return sdfTime.format(date);
    }

}
