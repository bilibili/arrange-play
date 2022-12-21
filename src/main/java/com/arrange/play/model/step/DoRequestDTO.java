package com.arrange.play.model.step;

import com.arrange.play.model.BaseStepRequestDTO;
import com.arrange.play.model.entity.inner.ReplaceMethod;
import java.util.List;
import java.util.Map;
import lombok.Data;

@Data
public class DoRequestDTO extends BaseStepRequestDTO {

  private Integer apiId;

  private Integer requestModuleId;

  private List<String> paramKeys;

  private Map<String, ReplaceMethod> replaceValue;

}
