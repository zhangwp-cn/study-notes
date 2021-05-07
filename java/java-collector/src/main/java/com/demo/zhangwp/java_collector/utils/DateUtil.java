package com.demo.zhangwp.java_collector.utils;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {
    private static final Log log = LogFactory.getLog(DateUtil.class);

    /**
     * 日期转换为指定格式是字符串
     *
     * @param date
     * @param format
     * @return
     */
    public static String Date2StrFormat(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);

        return sdf.format(date);
    }

    /**
     * 得到本月第一天的日期
     *
     * @return Date
     * @Methods Name getFirstDayOfMonth
     */
    public static Date getFirstDayOfMonth(Date date) {
        Calendar cDay = Calendar.getInstance();
        cDay.setTime(date);
        cDay.set(Calendar.DAY_OF_MONTH, 1);
        return cDay.getTime();
    }

    /**
     * 得到本月第一天的时间
     *
     * @return Date
     * @Methods Name getFirstDayZeroOfMonth
     */
    public static Date getFirstDayZeroOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 得到本月第一天的时间
     *
     * @return Date
     * @Methods Name getFirstDayZeroOfMonth
     */
    public static Date getFirstDayZeroOfMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 得到本月最后一天的日期
     *
     * @return Date
     * @Methods Name getLastDayOfMonth
     */
    public static Date getLastDayOfMonth(Date date) {
        Calendar cDay = Calendar.getInstance();
        cDay.setTime(date);
        cDay.set(Calendar.DAY_OF_MONTH, cDay.getActualMaximum(Calendar.DAY_OF_MONTH));
        return cDay.getTime();
    }

    public static Date getFirstDayOfMonthZero(Date date, int monthnum) {
        try {
            Date ss = DateUtils.round(date, Calendar.SECOND);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(ss);
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            calendar.add(Calendar.MONTH, monthnum);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            return calendar.getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date getFirstDayOfMonth(Date date, int monthnum) {
        try {
            Date ss = DateUtils.round(date, Calendar.SECOND);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(ss);
            calendar.add(Calendar.MONTH, monthnum);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            return calendar.getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 偏移日期 天
     *
     * @param date
     * @param daynum
     * @return
     */

    public static Date getDateByOffsetDay(Date date, int daynum) {
        try {
            Date ss = DateUtils.round(date, Calendar.SECOND);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(ss);
            calendar.add(Calendar.DATE, daynum);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            return calendar.getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


//    /**
//     * 套餐偏移周期
//     *
//     * @param date
//     * @param buyingCycle
//     * @param serviceKind
//     * @return
//     */
//    public static Date getCycleOffsetByServiceKind(Date date, int buyingCycle, ServiceKind serviceKind) {
//        Date date1 = null;
//        if (ServiceTypeEnum.ST_EXTRA.getCode().equals(serviceKind.getServiceType())) {
//            date1 = getFirstDayOfMonthZero(date, 1);
//        } else if (ServiceTypeEnum.ST_BASIC.getCode().equals(serviceKind.getServiceType())) {
//            date1 = getFirstDayOfMonthZero(date, buyingCycle);
//        } else if (ServiceTypeEnum.ST_MONTH_VOICE.getCode().equals(serviceKind.getServiceType())) {
//            date1 = getFirstDayOfMonthZero(date, buyingCycle);
//        } else if (ServiceTypeEnum.ST_INFINITY.getCode().equals(serviceKind.getServiceType())) {
//            date1 = getFirstDayOfMonthZero(date, buyingCycle);
//        } else if (ServiceTypeEnum.ST_MULTI.getCode().equals(serviceKind.getServiceType())) {
//            if ("MONTH".equals(serviceKind.getCycleUnit())) {
//                date1 = getDateByOffsetDay(getFirstDayOfMonth(date, serviceKind.getBuyingCycle()), 1);
//            } else {
//                date1 = getDateByOffsetDay(date, serviceKind.getBuyingCycle());
//            }
//        }
//        return date1;
//    }
//
//    /**
//     * 套餐偏移周期
//     *
//     * @param date
//     * @param serviceKindSpecs
//     * @param serviceKind
//     * @return
//     */
//    public static Date getCycleOffsetByServiceKind(Date date, ServiceKind serviceKind, ServiceKindSpecs serviceKindSpecs) {
//        Date date1 = null;
//        if (ServiceTypeEnum.ST_EXTRA.getCode().equals(serviceKind.getServiceType())) {
//            date1 = getFirstDayOfMonthZero(date, 1);
//        } else if (ServiceTypeEnum.ST_BASIC.getCode().equals(serviceKind.getServiceType())) {
//            date1 = getFirstDayOfMonthZero(date, serviceKindSpecs.getSpecs());
//        } else if (ServiceTypeEnum.ST_MONTH_VOICE.getCode().equals(serviceKind.getServiceType())) {
//            date1 = getFirstDayOfMonthZero(date, serviceKindSpecs.getSpecs());
//        } else if (ServiceTypeEnum.ST_INFINITY.getCode().equals(serviceKind.getServiceType())) {
//            date1 = getFirstDayOfMonthZero(date, serviceKindSpecs.getSpecs());
//        } else if (ServiceTypeEnum.ST_MULTI.getCode().equals(serviceKind.getServiceType())) {
//            if ("MONTH".equals(serviceKind.getCycleUnit())) {
//                date1 = getDateByOffsetDay(getFirstDayOfMonth(date, serviceKindSpecs.getSpecs()), 1);
//            } else {
//                date1 = getDateByOffsetDay(date, serviceKindSpecs.getSpecs());
//            }
//        }
//        return date1;
//    }
//
//    /**
//     * 套餐偏移周期
//     *
//     * @param date
//     * @return
//     */
//    public static Date getCycleOffsetByServiceKind(Date date, ServiceTypeEnum serviceTypeEnum, String cycleUnit, Integer spec) {
//        Date date1 = null;
//        if (ServiceTypeEnum.ST_EXTRA.equals(serviceTypeEnum)) {
//            date1 = getFirstDayOfMonthZero(date, 1);
//        } else if (ServiceTypeEnum.ST_BASIC.equals(serviceTypeEnum)) {
//            date1 = getFirstDayOfMonthZero(date, spec);
//        } else if (ServiceTypeEnum.ST_MONTH_VOICE.equals(serviceTypeEnum)) {
//            date1 = getFirstDayOfMonthZero(date, spec);
//        } else if (ServiceTypeEnum.ST_INFINITY.equals(serviceTypeEnum)) {
//            date1 = getFirstDayOfMonthZero(date, spec);
//        } else if (ServiceTypeEnum.ST_MULTI.equals(serviceTypeEnum)) {
//            if ("MONTH".equals(cycleUnit)) {
//                date1 = getDateByOffsetDay(getFirstDayOfMonth(date, spec), 1);
//            } else {
//                date1 = getDateByOffsetDay(date, spec);
//            }
//        }
//        return date1;
//    }

//    /**
//     * 套餐偏移周期
//     * @param date
//     * @param cycle
//     * @return
//     */
//    public static Date getCycleOffset(Date date , int  cycle, String cycleUnit){
//        Date date1 = null;
//        if("MONTH".equals(cycleUnit)){
//            date1 = getFirstDayOfMonthZero(date,cycle);
//        }else{
//            date1 = getDateByOffsetDay(date,cycle);
//        }
//
//        return date1;
//    }

    /**
     * 日期偏移天数
     *
     * @param date
     * @return
     */
    public static int getDayByDate(Date date) {
        if (date == null) date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 日期偏移月数
     *
     * @param date
     * @return
     */
    public static int getMonthByDate(Date date) {
        if (date == null) date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return (calendar.get(Calendar.MONTH) + 1);
    }

    /**
     * 日期偏移年
     *
     * @param date
     * @return
     */
    public static int getYearByDate(Date date) {
        if (date == null) date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    public static Date getTimeOffset(Integer offset, int unit) {
        Date date = new Date();//取时间
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(date); //需要将date数据转移到Calender对象中操作
        calendar.add(unit, offset);//偏移n分钟
        date = calendar.getTime();   //这个时间就是日期往后推一天的结果

        return date;
    }

    public static Date getTodayStartTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static Date dateStartTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }


    public static Date dateOffset(Date date, int intnum, Boolean isMonth) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        if (isMonth) {
            calendar.add(Calendar.MONTH, intnum);//日期偏移,正数向前,负数向后!
        } else {
            calendar.add(Calendar.DAY_OF_MONTH, intnum);//日期偏移,正数向前,负数向后!

        }
        return calendar.getTime();
    }


    public static Integer intervalDayNums(Date beforeDate, Date afterDate) {
        if (beforeDate == null) {
            return null;
        }
        if (afterDate == null) {
            return null;
        }
        return (int) ((afterDate.getTime() - beforeDate.getTime()) / (1000 * 3600 * 24));
    }
//
//    public static void main(String[] args) {
//        String dbtime1 = "2017-02-23";  //第二个日期
//
//        String dbtime2 = "2017-02-25";  //第一个日期
////算两个日期间隔多少天
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//        try {
//            Date date1 = format.parse(dbtime1);
//            Date date2 = format.parse(dbtime2);
//            Integer integer = intervalDayNums(date1, date2);
//            System.out.println();
//
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//    }

    /**
     * 日期是转换为(yyyy-MM-dd HH:mm:ss)格式字符串
     *
     * @param date
     * @return
     */
    public static String dateToString(Date date) {
        String strDateFormat = "yyyy-MM-dd HH:mm:ss";
        return dateToString(date, strDateFormat);
    }


    /**
     * 日期转换为指定格式字符串
     *
     * @param date
     * @param format
     * @return
     */
    public static String dateToString(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    /**
     * 默认格式（yyyy-MM-dd HH:mm:ss）字符串转换为日期
     *
     * @param dateStr
     * @return
     */
    public static Date strToDate(String dateStr) {
        return strToDate(dateStr, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 指定格式字符串转换为日期
     *
     * @param dateStr
     * @param format
     * @return
     */
    public static Date strToDate(String dateStr, String format) {
        Date date = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        try {
            date = simpleDateFormat.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    private static final LocalTime ONE_MINUTE_BEFORE_MIDNIGHT = LocalTime.of(23, 59, 0);
    private static final LocalTime ONE_MINUTE_AFTER_MIDNIGHT = LocalTime.of(0, 1, 0);
    public static final String MIDNIGHT_TYPE_ONE_MINUTE_BEFORE = "ONE_MINUTE_BEFORE";
    public static final String MIDNIGHT_TYPE_ONE_MINUTE_AFTER = "ONE_MINUTE_AFTER";

    public static boolean isAtMidnight(LocalTime time, String type) {
        if (MIDNIGHT_TYPE_ONE_MINUTE_BEFORE.equals(type)) {
            return time.isAfter(ONE_MINUTE_BEFORE_MIDNIGHT);
        } else if (MIDNIGHT_TYPE_ONE_MINUTE_AFTER.equals(type)) {
            return time.isBefore(ONE_MINUTE_AFTER_MIDNIGHT);
        } else {
            return time.isAfter(ONE_MINUTE_BEFORE_MIDNIGHT) || time.isBefore(ONE_MINUTE_AFTER_MIDNIGHT);
        }

    }

}