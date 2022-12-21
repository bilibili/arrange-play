package com.arrange.play.model.context;

import com.google.common.collect.Maps;
import java.util.Map;
import org.springframework.util.CollectionUtils;

public class ResponseContext {

  public static ThreadLocal<Map<String, Object>> resultMap = new ThreadLocal<>();

  public static void setStepResult(String stepName, Object stepResponse) {
    Map<String, Object> map =
        CollectionUtils.isEmpty(resultMap.get()) ? Maps.newLinkedHashMap() : resultMap.get();
    map.put(stepName, stepResponse);
    resultMap.set(map);
  }

  public static Map<String, Object> getResultMap() {
    return resultMap.get();
  }

  public static void clearResultMap() {
    resultMap.remove();
  }

}
