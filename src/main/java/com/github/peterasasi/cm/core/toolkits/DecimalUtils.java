package com.github.peterasasi.cm.core.toolkits;

public class DecimalUtils {

    /**
     * 保留小数点1位
     * @param n double
     * @return String
     */
    public static String formatDecimal(double n) {
        return String.format("%.1f", n);
    }

    /**
     * 保留小数点 decimalLength 位
     * @param n double
     * @param decimalLength int
     * @return String
     */
    public static String formatDecimal(double n, int decimalLength) {
        return String.format("%." + decimalLength + "f", n);
    }
}
