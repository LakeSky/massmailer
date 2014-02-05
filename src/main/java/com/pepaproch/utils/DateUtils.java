package com.pepaproch.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import org.joda.time.DateTimeZone;

public final class DateUtils {

    private static SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
    private static SimpleDateFormat dbdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    private static SimpleDateFormat eventDateFormatStr = new SimpleDateFormat("MM/dd/yyyy");

    private DateUtils() {
    }

    public static String date2String(Date date) {
        return sdf.format(date);
    }

    public static String date2String(Date date, SimpleDateFormat df) {
        return df.format(date);
    }

    public static Date string2Date(String dateString)
            throws ParseException {
        return new Date(sdf.parse(dateString).getTime());
    }

    public static Date string2Date(String dateString, String dateFormat)
            throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat(dateFormat);

        return new Date(format.parse(dateString).getTime());
    }

    public static int getElapsedDays(Date d1, Date d2) {
        return elapsed(d1, d2, 5);
    }

    public static List getDaysBetween(Date d1, Date d2) {
        ArrayList days = new ArrayList();
        int elapsed = elapsed(d1, d2, 5);
        for (int i = 0; i < elapsed; i++) {
            days.add(addDays(d1, i));
        }

        return days;
    }

    public static Date composeDateTime(Date d, String time) {
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        String[] timePart = time.split(":");
        c.set(Calendar.HOUR, Integer.valueOf(timePart[0]));
        c.set(Calendar.MINUTE, Integer.valueOf(timePart[1]));
        return c.getTime();
    }

    public static Date stripTime(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static String partOfdate(Date d1, int type) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(d1);
        int y = cal.get(type);
        return String.valueOf(y);
    }

    public static Date getLastDayOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        cal.set(Calendar.HOUR_OF_DAY, cal.getActualMaximum(Calendar.HOUR_OF_DAY));
        cal.set(Calendar.MINUTE, cal.getActualMaximum(Calendar.MINUTE));
        cal.set(Calendar.SECOND, cal.getActualMaximum(Calendar.SECOND));
        return cal.getTime();
    }

    public static Date getFirstDayOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        cal.set(Calendar.HOUR_OF_DAY, cal.getActualMinimum(Calendar.HOUR_OF_DAY));
        cal.set(Calendar.MINUTE, cal.getActualMinimum(Calendar.MINUTE));
        cal.set(Calendar.SECOND, cal.getActualMinimum(Calendar.SECOND));
        return cal.getTime();
    }

    public static Date getEndDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, cal.getActualMaximum(Calendar.HOUR_OF_DAY));
        cal.set(Calendar.MINUTE, cal.getActualMaximum(Calendar.MINUTE));
        cal.set(Calendar.SECOND, cal.getActualMaximum(Calendar.SECOND));
               cal.set(Calendar.MILLISECOND, cal.getActualMinimum(Calendar.MILLISECOND));
        return cal.getTime();
    }

    public static Date getStartDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, cal.getActualMinimum(Calendar.HOUR_OF_DAY));
        cal.set(Calendar.MINUTE, cal.getActualMinimum(Calendar.MINUTE));
        cal.set(Calendar.SECOND, cal.getActualMinimum(Calendar.SECOND));
              cal.set(Calendar.MILLISECOND, cal.getActualMinimum(Calendar.MILLISECOND));
        return cal.getTime();
    }

    public static int getElapsedMonths(Date d2, Date d1) {

        Calendar cal = Calendar.getInstance();
// default will be Gregorian in US Locales  
        cal.setTime(d1);
        int minuendMonth = cal.get(Calendar.MONTH);
        int minuendYear = cal.get(Calendar.YEAR);
        cal.setTime(d2);
        int subtrahendMonth = cal.get(Calendar.MONTH);
        int subtrahendYear = cal.get(Calendar.YEAR);

// the following will work okay for Gregorian but will not  
// work correctly in a Calendar where the number of months   
// in a year is not constant  
        return ((minuendYear - subtrahendYear) * cal.getMaximum(Calendar.MONTH)) + (minuendMonth - subtrahendMonth);

    }

    public static int getElapsedYears(Date d1, Date d2) {
        return elapsed(d1, d2, 1);
    }

    public static int getElapsedMin(Date d1, Date d2) {
        Long milisec = d1.getTime() - d2.getTime();
        Float elapsedTimeMin = milisec / (60 * 1000F);
        return elapsedTimeMin.intValue();
    }

    private static int elapsed(GregorianCalendar g1, GregorianCalendar g2, int type) {
        int elapsed = 0;
        GregorianCalendar gc1;
        GregorianCalendar gc2;
        if (g2.after(g1)) {
            gc2 = (GregorianCalendar) g2.clone();
            gc1 = (GregorianCalendar) g1.clone();
        } else {
            gc2 = (GregorianCalendar) g1.clone();
            gc1 = (GregorianCalendar) g2.clone();
        }
        if (type == 2 || type == 1) {
            gc1.clear(5);
            gc2.clear(5);
        }
        if (type == 1) {
            gc1.clear(2);
            gc2.clear(2);
        }
        while (gc1.before(gc2)) {
            gc1.add(type, 1);
            elapsed++;
        }
        return elapsed;
    }

    private static int elapsed(Date d1, Date d2, int type) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(d1);
        GregorianCalendar g1 = new GregorianCalendar(cal.get(1), cal.get(2), cal.get(5));
        cal.setTime(d2);
        GregorianCalendar g2 = new GregorianCalendar(cal.get(1), cal.get(2), cal.get(5));
        return elapsed(g1, g2, type);
    }

    public static int daysDifference(Date d1, Date d2) {
        return (int) ((d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));

    }

    public static Date addDays(Date date, int numOfDays) {
        Calendar calendar = GregorianCalendar.getInstance();
        if (date == null) {
            date = new Date();
        }
        calendar.setTime(date);
        calendar.add(5, numOfDays);
        return calendar.getTime();
    }

    public static Date addMonths(Date date, int numOfMonths) {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, numOfMonths);
        return calendar.getTime();
    }

    public static int dayOfTheMonth(Date d) {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(d);

        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    public static Date tomorrow() {
        return addDays(today(), 1);
    }

    public static Date today() {
        return new Date();
    }

    public static String getYear(Date date) {
        return partOfdate(date, 1);
    }

    public static SimpleDateFormat getDateFormat() {
        return sdf;
    }

    public static String date2DB(Date date) {
        return sdf.format(date);
    }

    public static String date2DB(Date date, SimpleDateFormat df) {
        return df.format(date);
    }

    public static String dateToEventDateString(Date d) {
        return eventDateFormatStr.format(d);
    }

    public static Boolean isTheSameDay(Date d1, Date d2) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(d1);
        cal2.setTime(d2);
        return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)
                && cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
    }

    public static Date DateUTC(Date d1) {
        DateTimeZone tz = DateTimeZone.getDefault();
        return new Date(tz.convertLocalToUTC(d1.getTime(), false));

    }

}
