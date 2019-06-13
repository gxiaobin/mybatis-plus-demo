package com.zm.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.joda.time.DateTime;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 日期时间公共类 使用Joda Time进行时间的计算，最后转换为java.util.Date
 *
 * @author administrator
 */
public class JodaTimeUtils {

    public final static String DF_LONG = "yyyy-MM-dd HH:mm:ss";

    public final static String DF_LONG_TIME = "yyyy-MM-dd HH:mm:ss.S";

    public final static String DF_SHORT = "yyyyMMddHHmmss";

    public final static String DF_YMD = "yyyy-MM-dd";

    public final static String DF_SHORT_YM = "yyyyMM";

    public final static String DF_SHORT_YMD = "yyMMdd";

    public final static String DF_TIME = "HH:mm:ss";

    public final static String DF_HM = "HH:mm";

    public static final FastDateFormat SDF = FastDateFormat.getInstance(DF_LONG);


    public static final int TIME_TYPE_MONTH = 0;
    public static final int TIME_TYPE_DAY = 1;
    public static final int TIME_TYPE_HOUR = 2;
    public static final int TIME_TYPE_MINUTE = 3;
    public static final int TIME_TYPE_SECOND = 4;

    /**
     * 在指定时间上加上或减去固定时间
     */
    public static Date addTime(Date date, int time, int timeType) {
        DateTime dateTime = new DateTime(date);
        DateTime newDate = null;
        if (timeType == TIME_TYPE_MONTH) {
            newDate = dateTime.plusMonths(time);
        } else if (timeType == TIME_TYPE_DAY) {
            newDate = dateTime.plusDays(time);
        } else if (timeType == TIME_TYPE_HOUR) {
            newDate = dateTime.plusHours(time);
        } else if (timeType == TIME_TYPE_MINUTE) {
            newDate = dateTime.plusMinutes(time);
        } else if (timeType == TIME_TYPE_SECOND) {
            newDate = dateTime.plusSeconds(time);
        } else {
            throw new RuntimeException("Unknow TimeType Parameter");
        }
        return newDate.toDate();
    }

    /**
     * 在指定时间上减去固定时间
     */
    public static Date minusTime(Date date, int time, int timeType) {
        DateTime dateTime = new DateTime(date);
        DateTime newDate = null;
        if (timeType == TIME_TYPE_MONTH) {
            newDate = dateTime.minusMonths(time);
        } else if (timeType == TIME_TYPE_DAY) {
            newDate = dateTime.minusDays(time);
        } else if (timeType == TIME_TYPE_HOUR) {
            newDate = dateTime.minusHours(time);
        } else if (timeType == TIME_TYPE_MINUTE) {
            newDate = dateTime.minusMinutes(time);
        } else if (timeType == TIME_TYPE_SECOND) {
            newDate = dateTime.minusSeconds(time);
        } else {
            throw new RuntimeException("Unknown TimeType Parameter");
        }
        return newDate.toDate();
    }

    public static String format(long date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date);
        return convertDate2String(DF_LONG_TIME, calendar.getTime());
    }

    public static String formatLongToStr(long date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date);
        return convertDate2String(DF_YMD, calendar.getTime());
    }


    /**
     * 日期转成字符串
     *
     * @param d
     * @param pattern 格式
     * @return
     */
    public static String dateToEngString(Date d, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.ENGLISH);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        return sdf.format(d);
    }

    /**
     * 字符串转成日期
     *
     * @param d
     * @param pattern 格式
     * @return
     */
    public static Date engStringToDate(String d, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.ENGLISH);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        try {
            return sdf.parse(d);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 在当前系统时间上加上或减去固定月份
     */
    public static Date addMonths(int month) {
        DateTime dateTime = new DateTime();
        return dateTime.plusMonths(month).toDate();
    }

    /**
     * 获取当前日期
     */
    public static Date getCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        return calendar.getTime();
    }

    public static Date getToday() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    public static Date getTomorrow() {
        return addTime(getToday(), 1, TIME_TYPE_DAY);
    }

    /**
     * 在指定日期上加上或减去固定月份
     */
    public static Date addMonths(Date date, int month) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusMonths(month).toDate();
    }

    /**
     * 将日期转换为字符串，如果未指定格式串，使用默认格式串
     */
    public static String convertDate2String(String format, Date date) {

        if (format == null) {
            return SDF.format(date);
        }
        SimpleDateFormat dateFormat = null;
        try {
            dateFormat = new SimpleDateFormat(format);
            return dateFormat.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将字符串转换为日期，如果未指定格式串，使用默认格式串
     */
    public static Date convertString2Date(String format, String dateStr) {
        Date date = null;
        try {
            if (format == null) {
                date = SDF.parse(dateStr);
            } else {
                SimpleDateFormat dateFormat = new SimpleDateFormat(format);
                date = dateFormat.parse(dateStr);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 获取缓存到期时间
     */
    public static Date getExpiredCacheDate(int minutes) {
        DateTime dateTime = new DateTime();
        return dateTime.plusMinutes(minutes).toDate();
    }

    public static String getStrDFLongDate(Date date) {
        return convertDate2String(DF_LONG, date);
    }


    public static Date getDFLongDate(String date) {
        return convertString2Date(DF_LONG, date);
    }


    public static String getStrCurrentDFLongDate() {
        return convertDate2String(DF_LONG, getCurrentDate());
    }


    public static String getDFShortDate() {
        return convertDate2String(DF_SHORT, getCurrentDate());
    }

    /**
     *
     * @param dateFormat
     * @return
     */
    public static String getStrCurrentDate(String dateFormat) {
        return convertDate2String(dateFormat, getCurrentDate());
    }


    public static String getStrDFTime() {
        return convertDate2String(DF_TIME, getCurrentDate());
    }

    /**
     * 昨天
     *
     * @return
     */
    public static Date getYesterDay() {
        return addTime(getCurrentDate(), -1, TIME_TYPE_DAY);
    }

    /**
     * 获取上个月
     *
     * @return
     */
    public static Date getPreMonth() {
        return addTime(getCurrentDate(), -1, TIME_TYPE_MONTH);
    }

    public static Date getFirstDayInCurMonth() {
        return getFirstDayInMonth(getCurrentDate());
    }

    public static Date getFirstDayInMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }

    public static Date getLastDayInCurMonth() {
        Date nextMonth = addTime(getFirstDayInCurMonth(), 1, TIME_TYPE_MONTH);
        return addTime(nextMonth, -1, TIME_TYPE_DAY);
    }


    /**
     * 获得两个日期之间相差天数.
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int diffDays(Date date1, Date date2) {
        return (int) (Math.abs(date1.getTime() - date2.getTime()) / 1000 / 60 / 60 / 24);
    }

    /**
     * 获得两个日期之间相差小时.
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int diffHour(Date date1, Date date2) {
        return (int) (Math.abs(date1.getTime() - date2.getTime()) / 1000 / 60 / 60);
    }

    /**
     * 判断输入的时间大于当前时间还是小雨当前时间
     *
     * @return true  or  false
     * @author 孔德强
     * @date 2015-12-02
     */
    public static boolean getSmallOrLargeTime(Date d1) {
        Date now = new Date();
        try {
            return now.before(d1);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 获取当前时间的小时开始
     *
     * @return Date
     */
    public static Date getNowHourStart() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        return calendar.getTime();
    }

    /**
     * 获取当前时间的小时结束
     *
     * @return Date
     */
    public static Date getNowHourEnd() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);

        return calendar.getTime();
    }

    /**
     * 获取本周的周日的日期
     *
     * @return
     */
    public static Date getNowWeekEnd() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        calendar.add(Calendar.WEEK_OF_YEAR, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }

    /**
     * 获取本周的周一的日期
     *
     * @return
     */
    public static Date getNowWeekStart() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取本月第一天日期
     *
     * @return
     */
    public static Date getNowMonthStart() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DATE));
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取本月最后一天日期
     *
     * @return
     */
    public static Date getNowMonthEnd() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);

        return calendar.getTime();
    }

    /**
     * 计算2个时间之间的整点
     */
    public static Set<Integer> getTimePointCount(String firstTime, String secondTime) {
        Set<Integer> set = new HashSet<Integer>();
        if (null != firstTime && !"".equals(firstTime)
                && null != secondTime && !"".equals(secondTime)) {
            Integer first = new Integer(firstTime.substring(0, 2));
            Integer second = new Integer(secondTime.substring(0, 2));
            for (int i = 0; i < second - first; i++) {
                if (first + i < second) {
                    set.add(first + i);
                }
            }
        }
        return set;
    }

    /**
     * 将2个时间段内的时间 以30分钟为单位放进集合
     */
    public static List<Date> getTimePointDateSet(Date firstTime, Date secondTime, int minuts) {
        List<Date> timeList = new ArrayList<>();
        if (null != firstTime && !"".equals(firstTime)
                && null != secondTime && !"".equals(secondTime)) {
            timeList.add(firstTime);//把开始时间加入集合
            Calendar cal = Calendar.getInstance();
            //使用给定的 Date 设置此 Calendar 的时间
            cal.setTime(firstTime);
            boolean bContinue = true;
            while (bContinue) {
                //根据日历的规则，为给定的日历字段添加或减去指定的时间量
                cal.add(Calendar.MINUTE, minuts);
                // 测试此日期是否在指定日期之后
                if (secondTime.after(cal.getTime())) {
                    timeList.add(cal.getTime());
                } else {
                    break;
                }
            }
            timeList.add(secondTime);//把结束时间加入集合

        }
        return timeList;
    }

    /**
     * 计算开始时间点之后n分钟后的时间点
     * minutes 分钟数
     */
    public static Date getEndTimeByStartTimeByMinuts(Date startDate, int minutes) {
        List<Date> timeList = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);
        cal.add(Calendar.MINUTE, minutes);
        return cal.getTime();
    }

    /**
     * 获取固定间隔时刻集合
     *
     * @param start    开始时间
     * @param end      结束时间
     * @param interval 时间间隔(单位：分钟)
     * @return
     */
    public static List<String> getIntervalTimeList(String start, String end, int interval) {
        Date startDate = JodaTimeUtils.convertString2Date(DF_TIME, start);
        Date endDate = JodaTimeUtils.convertString2Date(DF_TIME, end);
        List<String> list = new ArrayList<>();
        while (startDate.getTime() <= endDate.getTime()) {
            list.add(JodaTimeUtils.convertDate2String(DF_TIME, startDate));
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(startDate);
            calendar.add(Calendar.MINUTE, interval);
            if (calendar.getTime().getTime() > endDate.getTime()) {
                if (!startDate.equals(endDate)) {
                    list.add(JodaTimeUtils.convertDate2String(DF_TIME, endDate));
                }
                startDate = calendar.getTime();
            } else {
                startDate = calendar.getTime();
            }

        }
        return list;
    }

    /**
     * HH:mm:ss 转 string
     *
     * @param time
     * @return
     */
    public static String timeToStr(Date time) {
        SimpleDateFormat sdf = new SimpleDateFormat(DF_HM);
        return sdf.format(time);
    }

    /**
     * 当前时间格式化HH:mm:ss
     *
     * @return
     */
    public static Date strToDate() {
        SimpleDateFormat sdf = new SimpleDateFormat(DF_TIME);
        String d = sdf.format(System.currentTimeMillis());
        Date createDate = null;
        try {
            createDate = sdf.parse(d);
        } catch (Exception ce) {
            ce.printStackTrace();
        }
        return createDate;
    }

    /**
     * 带参String转date
     *
     * @return
     */
    public static Date strToDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat(DF_YMD);
        Date createDate = null;
        try {
            createDate = sdf.parse(date);
        } catch (Exception ce) {
            ce.printStackTrace();
        }
        return createDate;
    }

    public static Date strToDateYYYYMMDDHHMMSS(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat(DF_LONG);
        Date createDate = null;
        try {
            createDate = sdf.parse(date);
        } catch (Exception ce) {
            ce.printStackTrace();
        }
        return createDate;
    }

    /**
     * 获取年
     *
     * @return
     */
    public static Integer getYear() {
        Calendar a = Calendar.getInstance();
        return a.get(Calendar.YEAR);
    }

    /**
     * 获取上月
     *
     * @return
     */
    public static Integer getMonth() {
        Calendar a = Calendar.getInstance();
        return a.get(Calendar.MONTH);
    }

    public static String getBeforeDateYYMM() {
        SimpleDateFormat format = new SimpleDateFormat(DF_SHORT_YM);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        return format.format(calendar.getTime());
    }

    public static String getBeforeFirstMonthdateYYMMDD() {
        SimpleDateFormat format = new SimpleDateFormat(DF_YMD);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return format.format(calendar.getTime());
    }

    public static String getdateYYMMDD() {
        SimpleDateFormat format = new SimpleDateFormat(DF_YMD);
        Calendar calendar = Calendar.getInstance();
        return format.format(calendar.getTime());
    }

    /**
     * 获取上个月开始时间
     */
    public static String getBeforeFirstMonthdate() {
        SimpleDateFormat format = new SimpleDateFormat(DF_LONG);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return format.format(calendar.getTime());
    }

    /**
     * 获取上个月结束时间
     */
    public static String getBeforeLastMonthdate() {
        SimpleDateFormat format = new SimpleDateFormat(DF_LONG);
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return format.format(calendar.getTime());
    }

    /**
     * 时间范围搜索 开始时间格式化 yyyy-mm-dd 转为 yyyy-mm-dd 00:00:00
     *
     * @param
     */
    public static String getBeginTime(String beginTime) {
        if (StringUtils.isNotBlank(beginTime)) {
            return beginTime + " 00:00:00";
        }
        return "";
    }

    /**
     * 时间范围搜索 开始时间格式化 yyyy-mm-dd 转为 yyyy-mm-dd 23:59:59
     */
    public static String getEndTime(String endTime) {
        if (StringUtils.isNotBlank(endTime)) {
            return endTime + "23:59:59";
        }
        return "";
    }

    public static void main(String[] args) {
    }

}

