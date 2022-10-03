package com.volvocars.tax.congestion.calculator.util;

import org.apache.commons.lang3.time.DateUtils;

import java.util.*;

public class CalculationUtil {
    public static boolean checkPublicHolidays(
            Date publicHolidayDate, Date date, boolean isDayBeforeConcession) {
        if (DateUtils.isSameDay(publicHolidayDate, date)) return true;
        if (isDayBeforeConcession) {
            DateUtils.addDays(date, 1);
            if (DateUtils.isSameDay(publicHolidayDate, date)) return true;
        }
        return false;
    }

    public static boolean checkNoTaxMonth(String noTaxMonth, Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        if (noTaxMonth.equals("JANUARY")) {
            return Calendar.JANUARY == cal.get(Calendar.MONTH);
        }
        if (noTaxMonth.equals("FEBRUARY")) {
            return Calendar.FEBRUARY == cal.get(Calendar.MONTH);
        }
        if (noTaxMonth.equals("MARCH")) {
            return Calendar.MARCH == cal.get(Calendar.MONTH);
        }
        if (noTaxMonth.equals("APRIL")) {
            return Calendar.APRIL == cal.get(Calendar.MONTH);
        }
        if (noTaxMonth.equals("MAY")) {
            return Calendar.MAY == cal.get(Calendar.MONTH);
        }
        if (noTaxMonth.equals("JUNE")) {
            return Calendar.JUNE == cal.get(Calendar.MONTH);
        }
        if (noTaxMonth.equals("JULY")) {
            return Calendar.JULY == cal.get(Calendar.MONTH);
        }
        if (noTaxMonth.equals("AUGUST")) {
            return Calendar.AUGUST == cal.get(Calendar.MONTH);
        }
        if (noTaxMonth.equals("SEPTEMBER")) {
            return Calendar.SEPTEMBER == cal.get(Calendar.MONTH);
        }
        if (noTaxMonth.equals("OCTOBER")) {
            return Calendar.OCTOBER == cal.get(Calendar.MONTH);
        }
        if (noTaxMonth.equals("NOVEMBER")) {
            return Calendar.NOVEMBER == cal.get(Calendar.MONTH);
        }
        if (noTaxMonth.equals("DECEMBER")) {
            return Calendar.DECEMBER == cal.get(Calendar.MONTH);
        }
        return false;
    }
    public static void sortDate(List<Date> dates) {
        Collections.sort(dates, new Comparator<Date>() {
            @Override
            public int compare(Date object1, Date object2) {
                return object1.compareTo(object2);
            }
        });
    }

    public static boolean checkWeekend(String weekendDay, Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        if (weekendDay.equals("SUNDAY")) {
            return Calendar.SUNDAY == cal.get(Calendar.DAY_OF_WEEK);
        }
        if (weekendDay.equals("MONDAY")) {
            return Calendar.SUNDAY == cal.get(Calendar.DAY_OF_WEEK);
        }
        if (weekendDay.equals("TUESDAY")) {
            return Calendar.SUNDAY == cal.get(Calendar.DAY_OF_WEEK);
        }
        if (weekendDay.equals("WEDNESDAY")) {
            return Calendar.SUNDAY == cal.get(Calendar.DAY_OF_WEEK);
        }
        if (weekendDay.equals("THURSDAY")) {
            return Calendar.SUNDAY == cal.get(Calendar.DAY_OF_WEEK);
        }
        if (weekendDay.equals("FRIDAY")) {
            return Calendar.SUNDAY == cal.get(Calendar.DAY_OF_WEEK);
        }
        if (weekendDay.equals("SATURDAY")) {
            return Calendar.SUNDAY == cal.get(Calendar.DAY_OF_WEEK);
        }
        return false;
    }
}
