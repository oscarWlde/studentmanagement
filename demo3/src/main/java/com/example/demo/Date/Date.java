package com.example.demo.Date;

import java.text.SimpleDateFormat;
import java.util.*;
public class Date extends java.util.Date {
    public static void main(String[] args) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        java.util.Date date1 =  sdf.parse("2016-12-03");
        java.util.Date date2 =  sdf.parse(sdf.format(new Date()));

        int dayOffset = calcDayOffset(date1, date2);
        System.out.println("dayOffset:" + dayOffset);

        int weekOffset = calcWeekOffset(date1, date2);
        System.out.println("weekOffset:" + weekOffset);
    }


    /**
     * http://www.cnblogs.com/0201zcr/p/5000977.html
     * date2比date1多的天数
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int calcDayOffset(java.util.Date date1, java.util.Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        int day1 = cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);

        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        if (year1 != year2) {  //同一年
            int timeDistance = 0;
            for (int i = year1; i < year2; i++) {
                if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0) {  //闰年
                    timeDistance += 366;
                } else {  //不是闰年

                    timeDistance += 365;
                }
            }
            return timeDistance + (day2 - day1);
        } else { //不同年
            return day2 - day1;
        }
    }

    /**
     * date2比date1多的周数
     * @param startTime
     * @param endTime
     * @return
     */
    public static int calcWeekOffset(java.util.Date startTime, java.util.Date endTime) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(startTime);
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        dayOfWeek = dayOfWeek - 1;
        if (dayOfWeek == 0) dayOfWeek = 7;

        int dayOffset = calcDayOffset(startTime, endTime);

        int weekOffset = dayOffset / 7;
        int a;
        if (dayOffset > 0) {
            a = (dayOffset % 7 + dayOfWeek > 7) ? 1 : 0;
        } else {
            a = (dayOfWeek + dayOffset % 7 < 1) ? -1 : 0;
        }
        weekOffset = weekOffset + a;
        return weekOffset;
    }
}