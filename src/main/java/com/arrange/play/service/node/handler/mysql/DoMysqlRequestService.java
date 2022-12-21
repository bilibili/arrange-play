package com.arrange.play.service.node.handler.mysql;

import com.arrange.play.dao.mysql.service.MysqlInfoService;
import com.arrange.play.dao.mysql.service.SqlModuleService;
import com.arrange.play.enums.ResultEnum;
import com.arrange.play.exception.SqlExcuteFailException;
import com.arrange.play.model.entity.MysqlInfo;
import com.arrange.play.model.entity.SqlModule;
import com.arrange.play.model.step.DoRequestDTO;
import com.arrange.play.model.step.DoResponseDTO;
import com.arrange.play.model.step.MysqlRequestDTO;
import com.arrange.play.model.step.MysqlResponseDTO;
import com.arrange.play.service.node.handler.DoRequestBaseService;
import com.arrange.play.util.ResponseUtils;
import com.arrange.play.util.SpringContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DoMysqlRequestService extends DoRequestBaseService {

  @Autowired
  private MysqlInfoService mysqlInfoService;

  @Autowired
  private SqlModuleService sqlModuleService;

  @Override
  public DoResponseDTO doRequest(DoRequestDTO doRequestDTO) {
    DoResponseDTO doResponseDTO = new DoResponseDTO();
    // 查询sql所需链接信息
    MysqlInfo mysqlInfo = mysqlInfoService.selectOneRecordById(doRequestDTO.getApiId());
    if (mysqlInfo == null) {
      ResponseUtils.makeApiResponse(doResponseDTO, ResultEnum.NO_DATA, "获取mysql链接信息失败");
      return doResponseDTO;
    }
    // 查询请求模板
    SqlModule sqlModule = sqlModuleService.selectOneRecordById(doRequestDTO.getRequestModuleId());
    if (sqlModule == null) {
      ResponseUtils.makeApiResponse(doResponseDTO, ResultEnum.NO_DATA, "获取sql模板信息失败");
      return doResponseDTO;
    }

    MysqlRequestDTO mysqlRequestDTO = new MysqlRequestDTO();
    mysqlRequestDTO.setStepName(doRequestDTO.getStepName());
    mysqlRequestDTO.setMysqlInfo(mysqlInfo);
    mysqlRequestDTO.setReplaceValue(doRequestDTO.getReplaceValue());
    mysqlRequestDTO.setSqlModule(sqlModule);

    try {
      // 获取处理类
      MysqlRequest mysqlRequest = (MysqlRequest) SpringContextUtil.getBean(getMysqlRequest(sqlModule));
      MysqlResponseDTO mysqlResponseDTO = mysqlRequest.request(mysqlRequestDTO);

      mysqlRequest.dealResponse(mysqlResponseDTO, mysqlRequestDTO.getStepName());

      BeanUtils.copyProperties(mysqlResponseDTO, doResponseDTO);
    } catch (BeansException e) {
      log.error("获取bean失败: {}", getMysqlRequest(sqlModule));
      ResponseUtils.makeApiResponse(doResponseDTO, ResultEnum.SYSTEM_ERROR, "获取bean失败");
    } catch (SqlExcuteFailException e) {
      log.error("sql执行失败: {}", e.getMessage(), e.fillInStackTrace());
      ResponseUtils.makeApiResponse(doResponseDTO, ResultEnum.SYSTEM_ERROR, "获取bean失败");
    }

    return doResponseDTO;
  }

  private String getMysqlRequest(SqlModule sqlModule) {
    return sqlModule.getSqlType() + "Request";
  }

}
