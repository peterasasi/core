package com.github.peterasasi.cm.core.toolkits;

import java.util.Map;

public class MapGetter {

    /**
     * 获取 int 型值
     * @param map Map
     * @param key String
     * @param defaultValue String
     * @return int
     */
    public static int integerVal(Map<String, Object> map, String key, String defaultValue) {
        return Integer.valueOf(String.valueOf(map.getOrDefault(key, defaultValue)));
    }

    public static String strVal(Map<String, Object> map, String key, String defaultValue) {
        return String.valueOf(map.getOrDefault(key, defaultValue));
    }
}
