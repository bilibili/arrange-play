package com.arrange.play.model.entity.inner;

import java.util.Map;
import lombok.Data;

/**
 * flow入参
 */
@Data
public class ReplaceValue {

  private Map<String, ReplaceMethod> replaceMethod;

}
