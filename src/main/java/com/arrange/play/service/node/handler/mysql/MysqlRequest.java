package com.arrange.play.service.node.handler.mysql;

import com.arrange.play.exception.StepParamNullException;
import com.arrange.play.model.entity.inner.ReplaceMethod;
import com.arrange.play.model.step.MysqlRequestDTO;
import com.arrange.play.model.step.MysqlResponseDTO;
import com.arrange.play.util.CreateDate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

@Slf4j
public abstract class MysqlRequest {

  public abstract MysqlResponseDTO request(MysqlRequestDTO mysqlRequestDTO);

  public void dealParams(MysqlRequestDTO mysqlRequestDTO) {
    if (CollectionUtils.isEmpty(mysqlRequestDTO.getSqlModule().getParamKeys())) {
      return;
    }

    String sql = mysqlRequestDTO.getSqlModule().getSqlModuleContent();

    for (String key : mysqlRequestDTO.getSqlModule().getParamKeys()) {
      ReplaceMethod value = mysqlRequestDTO.getReplaceValue().get(key);
      if (value == null) {
        log.info("stepNode: {}, 缺少入参: {}", mysqlRequestDTO.getStepName(), key);
        throw new StepParamNullException("stepNode:" + mysqlRequestDTO.getStepName() + "缺少入参: " + key);
      }

      String resultValue = CreateDate.getValue(value);
      sql = sql.replaceAll("\\$\\{" + key + "}", resultValue);
    }
    mysqlRequestDTO.getSqlModule().setSqlModuleContent(sql);
  }

  public abstract void dealResponse(MysqlResponseDTO mysqlResponseDTO, String stepName);



}
