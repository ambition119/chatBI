package com.am.chat.bi.comm;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

public class Utils {
    private static final Logger LOG = LoggerFactory.getLogger(Utils.class);
    public static String getStringDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(date);
        return dateString;
    }

    public static Date getStringToDate(String date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date result = formatter.parse(date);
            return result;
        } catch (ParseException e) {
            e.printStackTrace();
            LOG.error("string date 解析出错", e);
        }
        return null;
    }
}

