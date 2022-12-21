package com.arrange.play.model;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

@Data
public class Step {

  private String stepName;

  private String stepType;

  private JSONObject stepContent;

}
