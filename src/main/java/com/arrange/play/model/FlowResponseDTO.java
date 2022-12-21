package com.arrange.play.model;

import java.util.Map;
import lombok.Data;

@Data
public class FlowResponseDTO extends BaseResponse {

  private Map<String, Object> resultMap;

}
