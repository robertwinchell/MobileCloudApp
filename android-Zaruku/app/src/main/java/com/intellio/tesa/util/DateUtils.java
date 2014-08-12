package com.intellio.tesa.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by stone_000 on 7/30/2014.
 */
public class DateUtils {

        public static String formatShortDate(Date date) {
               String formatStr = "yyyy-MM-dd";
               SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
                String temp = sdf.format(date);
                return temp;
            }
    public static Date fromShortDateString(String input) throws ParseException {
        String formatStr = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
        return sdf.parse(input);
    }

}
