package com.arrange.play.model.step;

import com.alibaba.fastjson.JSONArray;
import com.arrange.play.model.BaseResponse;
import lombok.Data;

@Data
public class MysqlResponseDTO extends BaseResponse {

  private JSONArray selectResult;

}
