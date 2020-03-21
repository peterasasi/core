package com.github.peterasasi.cm.core.toolkits;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    /**
     * 获取时间戳 秒
     * @return Long 时间戳
     */
    public static Long getCurrentTimeSeconds() {
        return System.currentTimeMillis() / 1000;
    }

    /**
     * 格式化时间戳
     * @param time Long 时间戳 秒
     * @param format 格式 yyyy-MM-dd HH:mm:ss
     * @return String 格式化的日期
     */
    public static String formatTime(Long time, String format) {
        return new SimpleDateFormat(format).format(new Date(time * 1000));
    }
}
