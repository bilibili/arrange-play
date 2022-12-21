package com.arrange.play.model.sample;

import com.arrange.play.model.BaseRequest;
import lombok.Data;

@Data
public class SampleRequestDTO extends BaseRequest {

  private String name;

  private String value;

}
