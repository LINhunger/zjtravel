package com.zjtravel.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by hunger on 2017/3/22.
 */
public class FormatUtil {

    /**
     * String转日期
     * @param time
     * @throws ParseException
     */
    public static Date stringToDate(String time) throws ParseException {
        SimpleDateFormat sdf  =   new  SimpleDateFormat( "yyyy-MM-dd" );
        return  sdf.parse(time);
    }
}
