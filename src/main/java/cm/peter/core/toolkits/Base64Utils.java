package cm.peter.core.toolkits;

import java.util.Base64;

public class Base64Utils {

    public static String encode(byte[] bytes) {
        return Base64.getEncoder().encodeToString(bytes);
    }

    public static String encode(String str) {
        return Base64.getEncoder().encodeToString(str.getBytes());
    }
    public static byte[] encodeBuffer(String str) {
        return Base64.getEncoder().encode(str.getBytes());
    }

    public static String decode(String decodeStr) {
        return new String(Base64.getDecoder().decode(decodeStr.getBytes()));
    }

    public static byte[] decodeBuffer(String decodeStr) {
        return Base64.getDecoder().decode(decodeStr.getBytes());
    }
    public static byte[] decodeBuffer(byte[] bytes) {
        return Base64.getDecoder().decode(bytes);
    }

    public static String decode(byte[] bytes) {
        return new String(Base64.getDecoder().decode(bytes));
    }
}
