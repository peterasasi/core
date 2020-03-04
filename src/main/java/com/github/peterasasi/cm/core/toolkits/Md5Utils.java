package com.github.peterasasi.cm.core.toolkits;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Utils {

    private final static String[] hexDigitsStrings = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d",
            "e", "f"};

    public static Boolean verifyMd5(String originStr, String md5) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        return encodeLowercase(originStr).equalsIgnoreCase(md5.toLowerCase());
    }

    public static String encodeUppercase(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        return encode(str, true);
    }

    public static String encodeLowercase(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        return encode(str, false);
    }

    public static String encode(String str, boolean uppercase) throws NoSuchAlgorithmException, UnsupportedEncodingException {

        MessageDigest m = MessageDigest.getInstance("MD5");
        m.update(str.getBytes(StandardCharsets.UTF_8));
        String result = byteArrayToHexString(m.digest());

        return uppercase ? result.toUpperCase() : result;
    }

    private static String byteArrayToHexString(byte[] bytes) {
        StringBuilder stringBuilder = new StringBuilder();
        for (byte tem : bytes) {
            stringBuilder.append(byteToHexString(tem));
        }
        return stringBuilder.toString();
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0) {
            n = 256 + n;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigitsStrings[d1] + hexDigitsStrings[d2];
    }

}
