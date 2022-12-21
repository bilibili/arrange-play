package com.arrange.play.model.step;

import com.arrange.play.model.BaseStepRequestDTO;
import com.arrange.play.model.entity.MysqlInfo;
import com.arrange.play.model.entity.SqlModule;
import com.arrange.play.model.entity.inner.ReplaceMethod;
import java.util.Map;
import lombok.Data;

@Data
public class MysqlRequestDTO extends BaseStepRequestDTO {

  private MysqlInfo mysqlInfo;

  private SqlModule sqlModule;

  private Map<String, ReplaceMethod> replaceValue;

}
