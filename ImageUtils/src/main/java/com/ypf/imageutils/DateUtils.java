package com.ypf.imageutils;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * 日期时间工具类
 * Created by guchenkai on 2015/11/26.
 */
public final class DateUtils {
    private static final String TAG = DateUtils.class.getSimpleName();
    private static final String[] weeks = new String[]{"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
    private static final SimpleDateFormat mDataFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat mTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final SimpleDateFormat mNosecondsFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    /**
     *
     * @param date  Date 格式的时间
     * @return  时间的年月日部分
     */
    public static String getAnyDate(String date, String formatNow, String formatYouwant){
        SimpleDateFormat df = new SimpleDateFormat(formatNow);//设置日期格式
       /* String date = df.format(new Date());
        System.out.println("1:"+date);*/

        //字符串转换成日期
        ParsePosition pos = new ParsePosition(0);
        java.util.Date datetime = df.parse(date, pos);

        //再转换成自定义的字符串
        SimpleDateFormat df2 = new SimpleDateFormat(formatYouwant);//设置日期格式
        String date2 = df2.format(datetime);
        return date2;
    }

    /**
     * 计算两个日期相差的天数，是否取绝对值
     *
     * @param date1 第一个日期
     * @param date2 第二个日期
     * @param isAbs 是否去绝对值
     * @return 相差天数
     */
    public static int getDaysUnAbs(String date1, String date2, boolean isAbs) {
        int day = 0;
        if (StringUtils.isEmpty(date1) || StringUtils.isEmpty(date2))
            return 0;
        try {
            Date date = mDataFormat.parse(date1);
            Date myDate = mDataFormat.parse(date2);
            day = (int) ((date.getTime() - myDate.getTime()) / (24 * 60 * 60 * 1000));
        } catch (ParseException e) {
            e.printStackTrace();
            Logger.e(TAG, e);
        }
        return isAbs ? Math.abs(day) : day;
    }

    /**
     * 计算两个日期相差的天数，是否取绝对值
     *
     * @param date1 第一个日期
     * @param date2 第二个日期
     * @return 相差天数
     */
    public static int getDaysUnAbs(String date1, String date2) {
        return getDaysUnAbs(date1, date2, true);
    }

    /**
     * 将长时间格式字符串转换为时间 yyyy-MM-dd HH:mm:ss
     *
     * @param date 日期
     * @return 长时间格式日期
     */
    public static Date strToDateLong(String date) {
        ParsePosition pos = new ParsePosition(0);
        return mTimeFormat.parse(date, pos);
    }

    /**
     * 获取当前的日期时间
     *
     * @return 当前的日期时间
     */
    public static String getCurrentTime() {
        return mTimeFormat.format(new Date());
    }

    /**
     * 获取当前的日期
     *
     * @return 当前的日期
     */
    public static String getCurrentDate() {
        return mDataFormat.format(new Date());
    }

    /**
     * 获取当前的日期
     *
     * @param patten 自定义的格式
     * @return 当前的日期
     */
    public static String getCurrentDate(String patten) {
        SimpleDateFormat mFormat = new SimpleDateFormat(patten);
        return mFormat.format(new Date());
    }

    /**
     * 获取输入日期是星期几
     *
     * @param date 日期
     * @return 星期几
     */
    public static String getWeekDate(Date date) {
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        calendar.setTime(date);
        return weeks[calendar.get(calendar.DAY_OF_WEEK) - 1];
    }

    /**
     * 获取输入日期是星期几
     *
     * @param date 日期
     * @return 星期几
     */
    public static String getWeekDate(String date) {
        try {
            Date d = mDataFormat.parse(date);
            return getWeekDate(d);
        } catch (ParseException e) {
            e.printStackTrace();
            Logger.e(TAG, e);
        }
        return "星期数未知";
    }

    /**
     * 毫秒转日期
     *
     * @param millisecond 毫秒数
     * @param pattern     正则式
     * @return 日期
     */
    public static String millisecondToDate(long millisecond, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        Date date = new Date(millisecond);
        return format.format(date);
    }

    /**
     * 秒转日期
     *
     * @param second  秒数
     * @param pattern 正则式
     * @return 日期
     */
    public static String secondToDate(int second, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        Date date = new Date(second * 1000L);
        return format.format(date);
    }

    /**
     * 时间计算
     *
     * @param millisecond 毫秒数
     * @return 计算后显示的文字
     */
    public static String timeCalculate(long millisecond) {
        String result = "";
        long diff = System.currentTimeMillis() - millisecond;//时间差
        if (diff < 1000 * 60 && diff > 0)
            return "刚刚以前";
        else if (diff >= 1000 * 60 && diff < 1000 * 60 * 60)
            return diff / (1000 * 60) + "分钟以前";
        else if (diff >= 1000 * 60 * 60 && diff < 1000 * 60 * 60 * 24)
            return diff / (1000 * 60 * 60) + "小时以前";
        else if (diff >= 1000 * 60 * 60 * 24)
            return diff / (1000 * 60 * 60 * 24) + "天以前";
        else if (diff < 0)
            return "输入时间在当前时间之后,不可以计算";
        return result;
    }

    /**
     * 时间计算
     *
     * @param second 秒数
     * @return 计算后显示的文字
     */
    public static String timeCalculate(int second) {
        return timeCalculate(second * 1000L);
    }

    /**
     * 把long时间转换成时间格式字符串
     *
     * @param time 时间
     * @return
     */
    public static String generateTime(long time) {
        int totalSeconds = (int) (time / 1000);
        int seconds = totalSeconds % 60;
        int minutes = (totalSeconds / 60) % 60;
        int hours = totalSeconds / 3600;
        return hours > 0 ? String.format("%02d:%02d:%02d", hours, minutes, seconds) : String.format("%02d:%02d", minutes, seconds);
    }

    /**
     * 获取之前几天或者之后几天
     *
     * @param date    输入的天数
     * @param diffDay 相差的天数
     * @param patten  patten
     * @return 之前几天或者之后几天
     */
    public static String getDiffDate(String date, int diffDay, String patten) {
        try {
            SimpleDateFormat mFormat = new SimpleDateFormat(patten);
            Date d = mFormat.parse(date);
            Calendar now = Calendar.getInstance();
            now.setTime(d);
            if (diffDay >= 0)
                diffDay = diffDay - 1;
            else
                diffDay = diffDay + 1;
            now.set(Calendar.DATE, now.get(Calendar.DATE) + diffDay);
            return mFormat.format(now.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}