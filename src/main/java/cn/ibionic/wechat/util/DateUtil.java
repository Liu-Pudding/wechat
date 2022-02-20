package cn.ibionic.wechat.util;

import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author ：Yuho Liu
 * @description：
 * @date ：2021/11/21 2:38 PM
 */
public class DateUtil {
    private static final long DAYTIME_MILLISECONDS = 86400000L;
    public static final String SDF_YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
    public static final String SDF_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public static final String SDF_YYYY_MM_DD = "yyyy-MM-dd";
    public static final String SDFYYYYMMDD = "yyyyMMdd";
    public static final String SDF_HH_MM = "HH:mm";
    public static final String SDF_YYYY = "yyyy";
    public static final String SDF_YYYY_DOT_M_DOT_D = "yyyy.M.d";
    public static final String SDF_YYYY_MM_DD_AT_HH_MM_SS = "yyyy-MM-dd@HH:mm:ss";

    public DateUtil() {
    }

    public static Date string2Date(String date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date d = null;
        try {
            d = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return d;
    }

    public static Date string2Date(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.parse(date);
    }

    public static String date2String(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String s = sdf.format(date);
        return s;
    }

    public static String date2String(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    public static Date rollByYear(Date date, int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(1, amount);
        return calendar.getTime();
    }

    public static Date rollByYear(int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(1, amount);
        return calendar.getTime();
    }

    public static Date rollByMonth(Date date, int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(2, amount);
        return calendar.getTime();
    }

    public static long rollByDay(Date date, int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(6, amount);
        return calendar.getTimeInMillis();
    }

    public static Date rollByDays(Date date, int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(6, amount);
        return calendar.getTime();
    }

    public static Date rollByMinutes(Date date, int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(12, amount);
        return calendar.getTime();
    }

    public static Date rollByHour(Date date, int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(11, amount);
        return calendar.getTime();
    }

    public static Date getNowDate() {
        return new Date();
    }

    public static String getNowDate(String pattern) {
        return date2String(new Date(), pattern);
    }

    public static String getDateFromTime(Date date) {
        SimpleDateFormat sdf_date = new SimpleDateFormat("yyyy-MM-dd");
        return sdf_date.format(date);
    }

    public static String formatTime(Date date, String format) throws ParseException {
        if (date == null) {
            return "";
        } else {
            format = !StringUtils.hasLength(format) ? "yyyy-MM-dd" : format;
            int result = differDate(date, new Date());
            --result;
            if (result < 1) {
                return getDate(date, "HH:mm发布");
            } else if (result >= 1 && result < 2) {
                return getDate(date, "1天前发布");
            } else if (result >= 2 && result < 3) {
                return getDate(date, "2天前发布");
            } else {
                return result >= 3 && result < 4 ? getDate(date, "3天前发布") : getDate(date, "yyyy-MM-dd");
            }
        }
    }

    public static String formatTime(String stime, String format) throws ParseException {
        if (stime == null) {
            return "";
        } else {
            Date ctime = string2Date(stime);
            return formatTime(ctime, format);
        }
    }

    public static int differDate(Date fromDate, Date endDate) throws ParseException {
        return differDate(date2String(fromDate, "yyyy-MM-dd"), date2String(endDate, "yyyy-MM-dd"));
    }

    public static int differDate(String fromDate, String endDate) throws ParseException {
        Date fDate = string2Date(fromDate, "yyyy-MM-dd");
        Date eDate = string2Date(endDate, "yyyy-MM-dd");
        int cha = calculateDiffDay(fDate, eDate);
        return cha >= 0 ? cha + 1 : cha - 1;
    }

    private static int calculateDiffDay(Date fDate, Date eDate) {
        long cha = eDate.getTime() - fDate.getTime();
        return (int)(cha / 86400000L);
    }

    public static String getDate(Date date, String formatStr) {
        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        return date != null ? format.format(date) : "";
    }

    public static long convert2long(String date, String format) {
        try {
            if (format == null || "".equals(format)) {
                format = "yyyy-MM-dd HH:mm:ss";
            }

            SimpleDateFormat sf = new SimpleDateFormat(format);
            return sf.parse(date).getTime();
        } catch (ParseException var3) {
            var3.printStackTrace();
        }

        return 0L;
    }

    public static String formatBBSTime(Date fDate) throws ParseException {
        Date oDate = new Date();
        long m = oDate.getTime() - fDate.getTime();
        long sec = m / 1000L;
        long min = m / 1000L / 60L;
        long hour = m / 1000L / 60L / 60L;
        long day = m / 1000L / 60L / 60L / 24L;
        long month = m / 1000L / 60L / 60L / 24L / 30L;
        long year = m / 1000L / 60L / 60L / 24L / 30L / 12L;
        if (year != 0L) {
            return year + "年前";
        } else if (month != 0L) {
            return month + "月前";
        } else if (day != 0L) {
            return day + "天前";
        } else if (hour != 0L) {
            return hour + "小时前";
        } else if (min != 0L) {
            return min + "分钟前";
        } else {
            return sec != 0L ? sec + "秒前" : "刚刚更新";
        }
    }

    public static Date calcDate(int year, int month, int day, int hour, int min, int sec) {
        Calendar cal = Calendar.getInstance();
        year += cal.get(Calendar.YEAR);
        month += cal.get(Calendar.MONTH);
        day += cal.get(Calendar.DATE);
        hour += cal.get(Calendar.HOUR_OF_DAY);
        min += cal.get(Calendar.MINUTE);
        sec += cal.get(Calendar.SECOND);
        cal.set(year, month, day, hour, min, sec);
        return cal.getTime();
    }

    public static String calcDate(int year, int month, int day, int hour, int min, int sec, String format) {
        Calendar cal = Calendar.getInstance();
        year += cal.get(Calendar.YEAR);
        month += cal.get(Calendar.MONTH);
        day += cal.get(Calendar.DATE);
        hour += cal.get(Calendar.HOUR_OF_DAY);
        min += cal.get(Calendar.MINUTE);
        sec += cal.get(Calendar.SECOND);
        cal.set(year, month, day, hour, min, sec);
        Date date = cal.getTime();
        return date2String(date, format);
    }

    public static int calcDate(String endDate, String startDate, String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);

        try {
            long t = formatter.parse(endDate).getTime() - formatter.parse(startDate).getTime();
            return (new Long(t / 86400000L)).intValue();
        } catch (ParseException var6) {
            return 0;
        }
    }

    public static String calcDate(String dateStr, int days, String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);

        try {
            long time = formatter.parse(dateStr).getTime();
            long t = time + (long)(days * 1000 * 24 * 60 * 60);
            Date d = new Date(t);
            return formatter.format(d);
        } catch (ParseException var9) {
            var9.printStackTrace();
            return dateStr;
        }
    }

    public static String calcDate(Date date, int seconds, String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);

        long time = date.getTime();
        long t = time + ((long) seconds * 1000);
        Date d = new Date(t);
        return formatter.format(d);
    }

    public static Date calcDate(Date date, int seconds) {
        long time = date.getTime();
        long t = time + ((long) seconds * 1000);
        return new Date(t);
    }

    public static int calcDays(String date, String format) {
        if (date == null) {
            return 5;
        } else {
            SimpleDateFormat formatter = new SimpleDateFormat(format);

            try {
                return (new Long((System.currentTimeMillis() - formatter.parse(date).getTime()) / 86400000L)).intValue();
            } catch (ParseException var4) {
                var4.printStackTrace();
                return 5;
            }
        }
    }

    public static String formatShowTime(String deliverTime, String fmtStr) {
        SimpleDateFormat sFmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;

        try {
            date = sFmt.parse(deliverTime);
        } catch (ParseException var5) {
            var5.printStackTrace();
        }

        return formatShowTime(date, fmtStr);
    }

    public static String formatShowTime(Date deliverTime, String fmtStr) {
        try {
            SimpleDateFormat fmt = new SimpleDateFormat(fmtStr);
            return fmt.format(deliverTime);
        } catch (Exception var3) {
            return "";
        }
    }
    public static String calcDate(int seconds, String format) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.SECOND, seconds);
        Date resultDate = cal.getTime();
        return date2String(resultDate, format);
    }

    public static Date calcDate(int days) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, days);
        Date resultDate = cal.getTime();
        return resultDate;
    }

    public static String calcDate(Date date, int year, int month, int day, int hour, int min, int sec, String format) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        year += cal.get(1);
        month += cal.get(2);
        day += cal.get(5);
        hour += cal.get(11);
        min += cal.get(12);
        sec += cal.get(13);
        cal.set(year, month, day, hour, min, sec);
        Date resultDate = cal.getTime();
        String date2String = date2String(resultDate, format);
        return date2String;
    }

    public static String getYear() {
        return date2String(new Date(), "yyyy");
    }

    public static int getYear(Date date) {
        return Integer.parseInt(date2String(date, "yyyy"));
    }

    public static int calCurrentYear(String year, String format) {
        SimpleDateFormat fmt = new SimpleDateFormat(format);
        Date now = new Date();
        String currentYear = fmt.format(now);
        return year.compareTo(currentYear);
    }
}
