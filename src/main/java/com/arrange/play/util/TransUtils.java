package com.arrange.play.util;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Joiner;
import com.google.common.collect.Maps;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TransUtils {

    public static Joiner.MapJoiner mapToUri = Joiner.on("&").withKeyValueSeparator("=");

    public static Map<String, String> jsonToMap(JSONObject object) {
        Map<String, String> map = Maps.newHashMap();
        for (String key : object.keySet()) {
            map.put(key, object.getString(key));
        }
        return map;
    }

}
