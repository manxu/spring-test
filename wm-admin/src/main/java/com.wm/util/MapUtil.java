package com.wm.util;

import java.util.HashMap;
import java.util.Map;

public class MapUtil {
    public Map<String, Object> map = new HashMap<>();

    public MapUtil com(String key, String value) {
        map.put(key, value);
        return this;
    }


}
