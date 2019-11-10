package com.techtest.jpmorgan.helper;

import com.techtest.jpmorgan.util.Currency;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * This class is used for date Util helper.
 */
public class DateHelper {

    public static final SimpleDateFormat DAY_START = new SimpleDateFormat("EE");
    public static final SimpleDateFormat FORMATTER = new SimpleDateFormat("dd MMM yyyy");

    /**
     * Get next working day for given date for currency specific weekends.
     * @param date Holiday
     * @param currency Currency of country
     */
    public static Date getNextWorkingDay(Date date, Currency currency) {
        String day = DAY_START.format(date);
        int additionalDays = currency.getWeekEndDays().indexOf(day) + 1;
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, additionalDays);
        return date;
    }

}
