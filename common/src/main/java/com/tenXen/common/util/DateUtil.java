package com.tenXen.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 工具类-日期处理
 *
 * @author xx
 * @version 1.0
 * @since 2014年1月28日
 */
public class DateUtil {

    /**
     * 获得当前日期
     *
     * @return
     */
    public static Date getNow() {
        Calendar cal = Calendar.getInstance();
        Date currDate = cal.getTime();
        return currDate;
    }

    /**
     * 日期转换为字符串 格式自定义
     *
     * @param date
     * @param f
     * @return
     */
    public static String dateStr(Date date, String f) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat format = new SimpleDateFormat(f);
        String str = format.format(date);
        return str;
    }

    /**
     * 日期转换为字符串 MM月dd日 hh:mm
     *
     * @param date
     * @return
     */
    public static String dateStr(Date date) {
        return dateStr(date, "MM月dd日 hh:mm");
    }

    /**
     * 日期转换为字符串 yyyy-MM-dd
     *
     * @param date
     * @return
     */
    public static String dateStr2(Date date) {
        return dateStr(date, "yyyy-MM-dd");
    }

    /**
     * yyyy年MM月dd日HH时mm分ss秒
     *
     * @param date
     * @return
     */
    public static String dateStr5(Date date) {
        return dateStr(date, "yyyy年MM月dd日 HH时mm分ss秒");
    }

    /**
     * yyyyMMddHHmmss
     *
     * @param date
     * @return
     */
    public static String dateStr3(Date date) {
        return dateStr(date, "yyyyMMddHHmmss");
    }

    /**
     * yyyy-MM-dd HH:mm:ss
     *
     * @param date
     * @return
     */
    public static String dateStr4(Date date) {
        return dateStr(date, "yyyy-MM-dd HH:mm:ss");

    }

    /**
     * yyyy年MM月dd日
     *
     * @param date
     * @return
     */
    public static String dateStr6(Date date) {
        return dateStr(date, "yyyy年MM月dd日");
    }

    /**
     * yyyyMMdd
     *
     * @param date
     * @return
     */
    public static String dateStr7(Date date) {
        return dateStr(date, "yyyyMMdd");
    }

    /**
     * MM-dd
     *
     * @param date
     * @return
     */
    public static String dateStr8(Date date) {
        return dateStr(date, "MM-dd");
    }

    /**
     * 将时间戳转换为Date
     *
     * @param times
     * @return
     */
    public static Date getDate(String times) {
        long time = Long.parseLong(times);
        return new Date(time * 1000);
    }

    public static String dateStr(String times) {
        return dateStr(getDate(times));
    }

    public static String dateStr2(String times) {
        return dateStr2(getDate(times));
    }

    public static String dateStr3(String times) {
        return dateStr3(getDate(times));
    }

    public static String dateStr4(String times) {
        return dateStr4(getDate(times));
    }

    public static String dateStr5(String times) {
        return dateStr5(getDate(times));
    }

    /**
     * 将Date转换为时间戳
     *
     * @param date
     * @return
     */
    public static long getTime(Date date) {
        return date.getTime() / 1000;
    }

    public static int getDay(Date d) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        return cal.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * s - 表示 "yyyy-mm-dd" 形式的日期的 String 对象
     *
     * @param s
     * @return
     */
    public static Date valueOf(String s) {
        final int YEAR_LENGTH = 4;
        final int MONTH_LENGTH = 2;
        final int DAY_LENGTH = 2;
        final int MAX_MONTH = 12;
        final int MAX_DAY = 31;
        int firstDash;
        int secondDash;
        int threeDash = 0;
        int fourDash = 0;
        Date d = null;

        if (s == null) {
            throw new IllegalArgumentException();
        }

        firstDash = s.indexOf('-');
        secondDash = s.indexOf('-', firstDash + 1);
        if (s.contains(":")) {
            threeDash = s.indexOf(':');
            fourDash = s.indexOf(':', threeDash + 1);
        }
        if ((firstDash > 0) && (secondDash > 0) && (secondDash < s.length() - 1)) {
            String yyyy = s.substring(0, firstDash);
            String mm = s.substring(firstDash + 1, secondDash);
            String dd = "";
            String hh = "";
            String MM = "";
            String ss = "";
            if (s.contains(":")) {
                dd = s.substring(secondDash + 1, threeDash - 3);
                hh = s.substring(threeDash - 2, threeDash);
                MM = s.substring(threeDash + 1, fourDash);
                ss = s.substring(fourDash + 1);
            } else {
                dd = s.substring(secondDash + 1);
            }
            if (yyyy.length() == YEAR_LENGTH && mm.length() == MONTH_LENGTH && dd.length() == DAY_LENGTH) {
                int year = Integer.parseInt(yyyy);
                int month = Integer.parseInt(mm);
                int day = Integer.parseInt(dd);
                int hour = 0;
                int minute = 0;
                int second = 0;
                if (s.contains(":")) {
                    hour = Integer.parseInt(hh);
                    minute = Integer.parseInt(MM);
                    second = Integer.parseInt(ss);
                }
                if (month >= 1 && month <= MAX_MONTH) {
                    int maxDays = MAX_DAY;
                    switch (month) {
                        // February determine if a leap year or not
                        case 2:
                            if ((year % 4 == 0 && !(year % 100 == 0)) || (year % 400 == 0)) {
                                maxDays = MAX_DAY - 2; // leap year so 29 days in
                                // February
                            } else {
                                maxDays = MAX_DAY - 3; // not a leap year so 28 days
                                // in February
                            }
                            break;
                        // April, June, Sept, Nov 30 day months
                        case 4:
                        case 6:
                        case 9:
                        case 11:
                            maxDays = MAX_DAY - 1;
                            break;
                    }
                    if (day >= 1 && day <= maxDays) {
                        Calendar cal = Calendar.getInstance();
                        cal.set(year, month - 1, day, hour, minute, second);
                        cal.set(Calendar.MILLISECOND, 0);
                        d = cal.getTime();
                    }
                }
            }
        }
        if (d == null) {
            throw new IllegalArgumentException();
        }
        return d;
    }

    /**
     * @param Begin
     * @param end   传入开始时间 和 结束时间 格式如：2012-09-07
     * @return 返回Map 获取相隔多少年 get("YEAR")及为俩个时间年只差，月 天，类推 Key ： YEAR MONTH DAY
     * 如果开始时间 晚于 结束时间 return null；
     * @author lijie
     */

    @SuppressWarnings({"rawtypes", "unchecked"})
    public static Map getApartTime(String Begin, String end) {
        String[] temp = Begin.split("-");
        String[] temp2 = end.split("-");
        if (temp.length > 1 && temp2.length > 1) {
            Calendar ends = Calendar.getInstance();
            Calendar begin = Calendar.getInstance();

            begin.set(StringUtil.toInt(temp[0]), StringUtil.toInt(temp[1]), StringUtil.toInt(temp[2]));
            ends.set(StringUtil.toInt(temp2[0]), StringUtil.toInt(temp2[1]), StringUtil.toInt(temp2[2]));
            if (begin.compareTo(ends) < 0) {
                Map map = new HashMap();
                ends.add(Calendar.YEAR, -StringUtil.toInt(temp[0]));
                ends.add(Calendar.MONTH, -StringUtil.toInt(temp[1]));
                ends.add(Calendar.DATE, -StringUtil.toInt(temp[2]));
                map.put("YEAR", ends.get(Calendar.YEAR));
                map.put("MONTH", ends.get(Calendar.MONTH) + 1);
                map.put("DAY", ends.get(Calendar.DATE));
                return map;
            }
        }
        return null;
    }

    /**
     * 前/后?分钟
     *
     * @param d
     * @param minute
     * @return
     */
    public static Date rollMinute(Date d, int minute) {
        return new Date(d.getTime() + minute * 60 * 1000);
    }

    /**
     * 前/后?天
     *
     * @param d
     * @param day
     * @return
     */
    public static Date rollDay(Date d, int day) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        cal.add(Calendar.DAY_OF_MONTH, day);
        return cal.getTime();
    }

    /**
     * 前/后?月
     *
     * @param d
     * @param mon
     * @return
     */
    public static Date rollMon(Date d, int mon) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        cal.add(Calendar.MONTH, mon);
        return cal.getTime();
    }

    /**
     * 前/后?年
     *
     * @param d
     * @param year
     * @return
     */
    public static Date rollYear(Date d, int year) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        cal.add(Calendar.YEAR, year);
        return cal.getTime();
    }

    public static Date rollDate(Date d, int year, int mon, int day) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        cal.add(Calendar.YEAR, year);
        cal.add(Calendar.MONTH, mon);
        cal.add(Calendar.DAY_OF_MONTH, day);
        return cal.getTime();
    }

    /**
     * 获取当前时间-时间戳字符串
     *
     * @return
     */
    public static String getNowTimeStr() {
        String str = Long.toString(System.currentTimeMillis() / 1000);
        return str;
    }

    /**
     * 获取当前时间-时间戳
     *
     * @return
     */
    public static int getNowTime() {
        return Integer.parseInt(StringUtil.isNull(System.currentTimeMillis() / 1000));
    }

    /**
     * 将Date转换为时间戳
     *
     * @param time
     * @return
     */
    public static String getTimeStr(Date time) {
        long date = time.getTime();
        String str = Long.toString(date / 1000);
        return str;
    }

    public static String getTimeStr(String dateStr, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date;
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
        String str = DateUtil.getTimeStr(date);
        return str;
    }

    public static String rollMonth(Date addTime, String time_limit) {
        Date t = DateUtil.rollDate(addTime, 0, StringUtil.toInt(time_limit), 0);
        return t.getTime() / 1000 + "";
    }

    public static String rollDay(Date addTime, String time_limit_day) {
        Date t = DateUtil.rollDate(addTime, 0, 0, StringUtil.toInt(time_limit_day));
        return t.getTime() / 1000 + "";
    }

    public static Date getIntegralTime() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static Date getLastIntegralTime() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static Date getLastSecIntegralTime(Date d) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(d.getTime());
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static long getTime(String format) {
        long t = 0;
        if (StringUtil.isBlank(format))
            return t;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date;
        try {
            date = sdf.parse(format);
            t = date.getTime() / 1000;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return t;
    }

    // 获取本周日的日期
    @SuppressWarnings("unused")
    public static String getCurrentWeekday() {
        int weeks = 0;
        int mondayPlus = DateUtil.getMondayPlus();
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, mondayPlus + 6);
        Date monday = currentDate.getTime();

        DateFormat df = DateFormat.getDateInstance();
        String preMonday = df.format(monday);
        return preMonday;
    }

    // 获得当前日期与本周日相差的天数
    private static int getMondayPlus() {
        Calendar cd = Calendar.getInstance();
        // 获得今天是一周的第几天，星期日是第一天，星期二是第二天......
        int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK) - 1; // 因为按中国礼拜一作为第一天所以这里减1
        if (dayOfWeek == 1) {
            return 0;
        } else {
            return 1 - dayOfWeek;
        }
    }

    // 获得本周一的日期
    @SuppressWarnings("unused")
    public static String getMondayOFWeek() {
        int weeks = 0;
        int mondayPlus = DateUtil.getMondayPlus();
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, mondayPlus);
        Date monday = currentDate.getTime();

        DateFormat df = DateFormat.getDateInstance();
        String preMonday = df.format(monday);
        return preMonday;
    }

    // 获取当前月第一天：
    public static String getFirstDayOfMonth(String first) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
        first = format.format(c.getTime());
        return first;
    }

    // 获取当月最后一天
    public static String getLastDayOfMonth(String last) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
        last = format.format(ca.getTime());
        return last;
    }

    /**
     * 日期月份处理
     *
     * @param d     时间
     * @param month 相加的月份，正数则加，负数则减
     * @return
     */
    public static Date timeMonthManage(Date d, int month) {
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(d);
        rightNow.add(Calendar.MONTH, month);
        return rightNow.getTime();
    }

    /**
     * 获取指定年月的最后一天
     *
     * @param year_time  指定年
     * @param month_time 指定月
     * @return
     */
    public static Date monthLastDay(int year_time, int month_time) {
        Calendar cal = Calendar.getInstance();
        cal.set(year_time, month_time, 0, 23, 59, 59);
        return cal.getTime();
    }

    /**
     * 获取指定年月的第一天
     *
     * @param year_time  指定年
     * @param month_time 指定月
     * @return
     */
    public static Date monthFirstDay(int year_time, int month_time) {
        Calendar cal = Calendar.getInstance();
        cal.set(year_time, month_time - 1, 1, 0, 0, 0);
        return cal.getTime();
    }

    /**
     * 获取指定时间月的第一天
     *
     * @param d 指定时间，为空代表当前时间
     * @return
     */
    public static Date currMonthFirstDay(Date d) {
        Calendar cal = Calendar.getInstance();
        if (d != null)
            cal.setTime(d);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE), 0, 0, 0);
        return cal.getTime();
    }

    /**
     * 获取指定时间月的最后一天
     *
     * @param d 指定时间，为空代表当前时间
     * @return
     */
    public static Date currMonthLastDay(Date d) {
        Calendar cal = Calendar.getInstance();
        if (d != null)
            cal.setTime(d);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE), 23, 59, 59);
        return cal.getTime();
    }

    /**
     * 获取指定时间的年
     *
     * @param date 指定时间
     * @return
     */
    public static int getTimeYear(Date date) {
        if (date == null)
            date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.YEAR);
    }

    /**
     * 获取指定时间的月
     *
     * @param date 指定时间
     * @return
     */
    public static int getTimeMonth(Date date) {
        if (date == null)
            date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取指定时间的天
     *
     * @param date 指定时间
     * @return
     */
    public static int getTimeDay(Date date) {
        if (date == null)
            date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DATE);
    }

    public static Date getFirstSecIntegralTime(Date d) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(d.getTime());
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.set(Calendar.DATE, 0);
        return cal.getTime();
    }

    /**
     * 获取指定时间天的结束时间
     *
     * @param d
     * @return
     */
    public static Date getDayEndTime(long d) {
        Date day = null;
        if (d <= 0) {
            day = new Date();
        } else {
            day = new Date(d * 1000);
        }
        Calendar cal = Calendar.getInstance();
        if (day != null) {
            cal.setTimeInMillis(day.getTime());
        }
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE), 23, 59, 59);
        return cal.getTime();
    }

    /**
     * 获取指定时间天的开始时间
     *
     * @param d
     * @return
     */
    public static Date getDayStartTime(long d) {
        Date day = null;
        if (d <= 0) {
            day = new Date();
        } else {
            day = new Date(d * 1000);
        }
        Calendar cal = Calendar.getInstance();
        if (day != null) {
            cal.setTimeInMillis(day.getTime());
        }
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE), 0, 0, 0);
        return cal.getTime();
    }

    /**
     * 获取19位的格式时间
     *
     * @param dateStr
     * @return
     * @throws ParseException
     */
    public static Date getDateByFullDateStr(String dateStr) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return sdf.parse(dateStr);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取16位的格式时间 yyyy-MM-dd HH:mm
     *
     * @param dateStr
     * @return
     * @throws ParseException
     */
    public static Date getDateByStr(String dateStr) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            return sdf.parse(dateStr);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取yyyy-MM-dd的格式时间
     *
     * @param dateStr
     * @return
     * @throws ParseException
     */
    public static Date getDateByDateStr(String dateStr) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return sdf.parse(dateStr);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 计算date2 - date1之间相差的天数
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int daysBetween(Date date1, Date date2) {
        DateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Calendar cal = Calendar.getInstance();
        try {
            Date d1 = sdf.parse(DateUtil.dateStr7(date1));
            Date d2 = sdf.parse(DateUtil.dateStr7(date2));
            cal.setTime(d1);
            long time1 = cal.getTimeInMillis();
            cal.setTime(d2);
            long time2 = cal.getTimeInMillis();
            return Integer.parseInt(String.valueOf((time2 - time1) / 86400000L));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 计算date2 - date1之间相差的天数
     *
     * @param date1
     * @param date2
     * @return
     */
    @SuppressWarnings("deprecation")
    public static int minutesBetween(Date date1, Date date2) {
        Calendar cal = Calendar.getInstance();
        date1.setSeconds(0);
        cal.setTime(date1);
        long time1 = cal.getTimeInMillis();
        cal.setTime(date2);
        long time2 = cal.getTimeInMillis();
        if (time2 - time1 <= 0) {
            return 0;
        } else {
            return Integer.parseInt(String.valueOf((time2 - time1) / 60000L)) + 1;
        }

    }

    public static void main(String[] args) {
        System.out.println(minutesBetween(getDateByFullDateStr("2015-12-25 12:23:39"), getDateByStr("2015-12-25 12:25")));
    }
}
