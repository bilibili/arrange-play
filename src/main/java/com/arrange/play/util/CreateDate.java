package com.arrange.play.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.arrange.play.enums.ValueFromEnum;
import com.arrange.play.exception.StepParamNullException;
import com.arrange.play.model.context.ResponseContext;
import com.arrange.play.model.entity.inner.ReplaceMethod;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

@Slf4j
public class CreateDate {

  private final static String LOCAL = "local";

  private final static String PRE_STEP = "step";

  private final static Integer KV = 2;

  public static String getValue(ReplaceMethod replaceMethod) {
    return getValue(replaceMethod.getFrom(), replaceMethod.getValue());
  }

  public static String getValue(String from, String value) {
    ValueFromEnum fromEnum = ValueFromEnum.getValueFromEnumMap(from);
    if (fromEnum == null) {
      log.info("数据来源未知");
      throw new StepParamNullException("未知的数据来源: " + from);
    }
    switch (fromEnum) {
      case LOCAL:
        return value;
      case DATE_ADD:
        return DateTimeUtil.getXDate(Integer.parseInt(value)).getTime() + "";
      case STEP:
        return getJSONPath(value);
      default:
        return "";
    }
  }

  public static String getJSONPath(String path) {
    String[] strings = StringUtils.split(path, "=");
    if (strings == null || strings.length != KV) {
      log.info("字符串解析异常: {}", path);
      return null;
    }
    try {
      JSONObject preStepRes = (JSONObject) ResponseContext.getResultMap().get(strings[0]);
      return JSONPath.read(preStepRes.toJSONString(), strings[1]) + "";
    } catch (Exception e) {
      return "";
    }
  }

}
