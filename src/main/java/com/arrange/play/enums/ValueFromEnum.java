package com.arrange.play.enums;

import com.google.common.collect.Maps;
import java.util.Map;

public enum ValueFromEnum {

  /**
   * 入参设置的默认值
   */
  LOCAL("local"),

  /**
   * 调用时间方法
   */
  DATE_ADD("date_add"),

  /**
   * 从前置步骤结果获取
   */
  STEP("step");

  private static final Map<String, ValueFromEnum> valueFromEnumMap;

  static {
    valueFromEnumMap = Maps.newHashMap();
    for (ValueFromEnum valueFromEnum : ValueFromEnum.values()) {
      valueFromEnumMap.put(valueFromEnum.getFrom(), valueFromEnum);
    }
  }

  ValueFromEnum(String from) {
    this.from = from;
  }

  private String from;

  public String getFrom() {
    return from;
  }

  public static ValueFromEnum getValueFromEnumMap(String from) {
    return valueFromEnumMap.get(from);
  }

}
