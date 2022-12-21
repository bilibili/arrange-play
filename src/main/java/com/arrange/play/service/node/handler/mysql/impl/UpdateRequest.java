package com.arrange.play.service.node.handler.mysql.impl;

import com.arrange.play.dao.mysql.con.Connector;
import com.arrange.play.enums.ResultEnum;
import com.arrange.play.exception.SqlExcuteFailException;
import com.arrange.play.exception.StepParamNullException;
import com.arrange.play.model.context.ResponseContext;
import com.arrange.play.model.step.MysqlRequestDTO;
import com.arrange.play.model.step.MysqlResponseDTO;
import com.arrange.play.service.node.handler.mysql.MysqlRequest;
import com.arrange.play.util.ResponseUtils;
import java.sql.Connection;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UpdateRequest extends MysqlRequest {

  @Override
  public MysqlResponseDTO request(MysqlRequestDTO mysqlRequestDTO) {
    MysqlResponseDTO mysqlResponseDTO = new MysqlResponseDTO();
    Connection conn = null;

    try {
      dealParams(mysqlRequestDTO);
      conn = Connector.getConn(mysqlRequestDTO.getMysqlInfo());
      int result = Connector
          .updateRecord(conn, mysqlRequestDTO.getSqlModule().getSqlModuleContent());
      if (result == 0) {
        log.info("sql执行失败: {}", mysqlRequestDTO.getSqlModule().getSqlModuleContent());
        throw new SqlExcuteFailException("insert sql执行失败: " + mysqlRequestDTO.getSqlModule().getSqlModuleContent());
      }

      ResponseUtils.makeApiResponse(mysqlResponseDTO, ResultEnum.SUCCESS);
    } catch (StepParamNullException e) {
      log.info("update sql却少入参: {}", e.getMessage(), e.fillInStackTrace());
      ResponseUtils.makeApiResponse(mysqlResponseDTO, ResultEnum.PARAM_NULL, e.getMessage());
    } catch (Exception e) {
      log.info("insert sql执行异常: {}", mysqlRequestDTO.getSqlModule().getSqlModuleContent(), e.fillInStackTrace());
      ResponseUtils.makeApiResponse(mysqlResponseDTO, ResultEnum.SYSTEM_ERROR);
    }

    return mysqlResponseDTO;
  }

  @Override
  public void dealResponse(MysqlResponseDTO mysqlResponseDTO, String stepName) {
    if (ResultEnum.SUCCESS.getErrorCode().equals(mysqlResponseDTO.getResponseCode())) {
      ResponseContext.setStepResult(stepName, "[]");
    }
  }
}
