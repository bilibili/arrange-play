package com.arrange.play.model;

import java.util.Map;
import lombok.Data;

@Data
public class FlowRequestDTO extends BaseRequest {

  private Integer flowId;

  private Map<String, String> requestParams;

}
