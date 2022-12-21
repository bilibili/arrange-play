package com.arrange.play.model.step;

import com.arrange.play.model.BaseStepRequestDTO;
import com.arrange.play.model.entity.HttpApiInfo;
import com.arrange.play.model.entity.HttpRequestModule;
import com.arrange.play.model.entity.inner.ReplaceMethod;
import com.arrange.play.model.entity.inner.ReplaceValue;
import java.util.Map;
import lombok.Data;

@Data
public class HttpRequestDTO extends BaseStepRequestDTO {

  private HttpApiInfo httpApiInfo;

  private HttpRequestModule httpRequestModule;

  private ReplaceValue replaceValues;

  private Map<String, ReplaceMethod> replaceValue;

}
