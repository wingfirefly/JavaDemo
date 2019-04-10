package com.zyy.time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.junit.Test;

public class DateCalendarDemo {

    @Test
    public void testDateFormat() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf.format(new Date()));
        try {
            System.out.println(sdf.parse("2016-2-2 22:22:22"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /*
     * HH: 24小时制(0-23) mm: 分 ss: 秒 S: 毫秒 E: 星期几 D: 一年中的第几天 F:
     * 一月中的第几个星期(会把这个月总共过的天数除以7) w: 一年中的第几个星期 W: 一月中的第几星期(会根据实际情况来算) a: 上下午标识 k:
     * 和HH差不多, 表示一天24小时制(1-24). K: 和hh差不多, 表示一天12小时制(0-11). z: 表示时区
     */

    @Test
    public void testCalendar() {
        Calendar c = Calendar.getInstance();
        System.out.println(c.get(Calendar.YEAR));
        System.out.println(c.get(Calendar.WEEK_OF_MONTH)); // 看日历
        System.out.println(c.get(Calendar.DAY_OF_WEEK));

        c.add(Calendar.YEAR, 1); // -1
        System.out.println(c.get(Calendar.YEAR));

        c.setTime(new Date());
        System.out.println(c.get(Calendar.YEAR));

        c.set(1, 2, 3);
        System.out.println(c.get(Calendar.YEAR));
        System.out.println(c.get(Calendar.MONTH));
        System.out.println(c.get(Calendar.DATE));
    }

    @Test
    public void testZeroTime() {
        Date date = getZeroTime(); // 假如只比较日期, 把时间信息清除
        System.out.println(date);
    }

    @Test
    public void testCalendarHour() {
        Date date = parseDate("2016-12-12 9:9:9");
        date = clearTime(date);
        System.out.println(date);

        date = parseDate("2016-12-12 19:19:19");
        date = clearTime(date);
        System.out.println(date);
    }

    private Date getZeroTime() {
        return clearTime(new Date());
    }

    private Date parseDate(String source) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return sdf.parse(source);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Date clearTime(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);

        c.set(Calendar.HOUR, 0); // HOUR: 12小时时钟, HOUR_OF_DAY: 24小时时钟
        // c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    @Test
    public void testSqlDate() {
        java.sql.Date sqlDate = new java.sql.Date(new Date().getTime());
        Date date = new Date(sqlDate.getTime());
        System.out.println(sqlDate);
        System.out.println(date);
    }

    @Test
    public void testTimeZone() {
        TimeZone defaultTimeZone = TimeZone.getDefault();
        System.out.println(defaultTimeZone);

        String[] availableIDs = TimeZone.getAvailableIDs(3600 * 1000 * 8);
        System.out.println(Arrays.toString(availableIDs));

        TimeZone timeZone = TimeZone.getTimeZone("Asia/Shanghai");
        TimeZone.setDefault(timeZone);

        String[] timeZoneIDS = TimeZone.getAvailableIDs();
        for (String timeZoneID : timeZoneIDS) {
            System.out.println(timeZoneID);
        }
    }

}
