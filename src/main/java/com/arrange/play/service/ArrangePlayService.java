package com.arrange.play.service;

import com.alibaba.fastjson.JSON;
import com.arrange.play.dao.mysql.service.RequestFlowService;
import com.arrange.play.enums.ResultEnum;
import com.arrange.play.enums.ValueFromEnum;
import com.arrange.play.model.FlowRequestDTO;
import com.arrange.play.model.FlowResponseDTO;
import com.arrange.play.model.context.ResponseContext;
import com.arrange.play.model.entity.RequestFlow;
import com.arrange.play.model.entity.inner.ReplaceMethod;
import com.arrange.play.model.entity.inner.StepNode;
import com.arrange.play.model.step.DoRequestDTO;
import com.arrange.play.model.step.DoResponseDTO;
import com.arrange.play.service.node.handler.DoRequestBaseService;
import com.arrange.play.util.ResponseUtils;
import com.arrange.play.util.SpringContextUtil;
import com.arrange.play.util.StringsUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ArrangePlayService {

  @Autowired
  private RequestFlowService requestFlowService;

  public FlowResponseDTO flow(FlowRequestDTO flowRequestDTO) {
    FlowResponseDTO flowResponseDTO = new FlowResponseDTO();
    log.info("执行流程id: {}", flowRequestDTO.getFlowId());

    // 查询flow
    RequestFlow requestFlow = requestFlowService
        .selectRequestFlowByFlowId(flowRequestDTO.getFlowId());
    if (requestFlow == null) {
      log.error("未找到流程id: {}", flowRequestDTO.getFlowId());
      ResponseUtils.makeApiResponse(flowResponseDTO, ResultEnum.NO_DATA);
      return flowResponseDTO;
    }

    log.info("开始执行流程: {}", JSON.toJSONString(requestFlow));

    // 入参处理
    for (String key : requestFlow.getReplaceValue().keySet()) {
      ReplaceMethod replaceMethod = requestFlow.getReplaceValue().get(key);
      if (!ValueFromEnum.LOCAL.getFrom().equals(replaceMethod.getFrom())) {
        continue;
      }
      String paramsValue = flowRequestDTO.getRequestParams().get(key);
      if (StringsUtils.isEmptyOrNull(paramsValue)) {
        log.info("参数预处理失败, 入参: {} 不存在", key);
        ResponseUtils.makeApiResponse(flowResponseDTO, ResultEnum.PARAM_NULL, key);
        return flowResponseDTO;
      }
      // 赋值
      requestFlow.getReplaceValue().get(key).setValue(paramsValue);
    }

    try {
      for (StepNode flow : requestFlow.getFlowContent()) {
        log.info("开始执行步骤: {}", flow.getStepName());
        if (flow.getWaitTime() != 0) {
          Thread.sleep(flow.getWaitTime());
        }
        DoRequestBaseService doRequestBaseService = (DoRequestBaseService) SpringContextUtil
            .getBean(getRequestService(flow.getStepType()));
        DoRequestDTO doRequestDTO = new DoRequestDTO();
        doRequestDTO.setStepName(flow.getStepName());
        doRequestDTO.setApiId(flow.getApiId());
        doRequestDTO.setRequestModuleId(flow.getRequestModuleId());
        doRequestDTO.setReplaceValue(requestFlow.getReplaceValue());

        DoResponseDTO doResponseDTO = doRequestBaseService.doRequest(doRequestDTO);
        if (!doResponseDTO.getResponseCode().equals(ResultEnum.SUCCESS.getErrorCode())) {
          log.info("执行到步骤: {} 失败, {}", flow.getStepName(), JSON.toJSONString(doResponseDTO));
          ResponseUtils
              .makeApiResponse(flowResponseDTO, ResultEnum.FLOW_STEP_FAIL, flow.getStepName());
          break;
        }
        log.info("执行步骤: {}成功", flow.getStepName());
      }
    } catch (InterruptedException e) {
      log.error("执行线程线程被中断step: {} ", requestFlow.getId(), e.fillInStackTrace());
      Thread.currentThread().interrupt();
    } catch (Exception e) {
      log.error("执行流程: {} 异常", JSON.toJSONString(flowRequestDTO), e.fillInStackTrace());
      ResponseUtils.makeApiResponse(flowResponseDTO, ResultEnum.SYSTEM_ERROR, e.getMessage());
    }

    // 步骤结果搜集
    flowResponseDTO.setResultMap(ResponseContext.getResultMap());
    ResponseUtils.makeApiResponse(flowResponseDTO, ResultEnum.SUCCESS);

    // 清除上下文
    ResponseContext.clearResultMap();
    return flowResponseDTO;
  }

  private String getRequestService(String stepType) {
    return "do" + stepType + "RequestService";
  }

}
