package com.arrange.play.service.node.handler.http;

import com.alibaba.fastjson.JSONException;
import com.arrange.play.dao.mysql.service.HttpApiInfoService;
import com.arrange.play.dao.mysql.service.HttpRequestModuleService;
import com.arrange.play.enums.ResultEnum;
import com.arrange.play.model.entity.HttpApiInfo;
import com.arrange.play.model.entity.HttpRequestModule;
import com.arrange.play.model.step.DoRequestDTO;
import com.arrange.play.model.step.DoResponseDTO;
import com.arrange.play.model.step.HttpRequestDTO;
import com.arrange.play.model.step.HttpResponseDTO;
import com.arrange.play.service.node.handler.DoRequestBaseService;
import com.arrange.play.util.ResponseUtils;
import com.arrange.play.util.SpringContextUtil;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DoHttpRequestService extends DoRequestBaseService {

  @Autowired
  private HttpApiInfoService httpApiInfoService;

  @Autowired
  private HttpRequestModuleService httpRequestModuleService;

  @Override
  public DoResponseDTO doRequest(DoRequestDTO doRequestDTO) {
    DoResponseDTO doResponseDTO = new DoResponseDTO();

    HttpApiInfo httpApiInfo = httpApiInfoService.selectApiInfoByApiId(doRequestDTO.getApiId());
    if (httpApiInfo == null) {
      ResponseUtils.makeApiResponse(doResponseDTO, ResultEnum.NO_DATA, "获取http接口信息失败");
      return doResponseDTO;
    }
    HttpRequestModule httpRequestModule = httpRequestModuleService
        .selectRequestModuleByRequestModuleId(doRequestDTO.getRequestModuleId());
    if (httpRequestModule == null) {
      ResponseUtils.makeApiResponse(doResponseDTO, ResultEnum.NO_DATA, "获取http接口模板信息失败");
      return doResponseDTO;
    }

    HttpRequestDTO httpRequestDTO = new HttpRequestDTO();
    httpRequestDTO.setStepName(doRequestDTO.getStepName());
    httpRequestDTO.setHttpApiInfo(httpApiInfo);
    httpRequestDTO.setHttpRequestModule(httpRequestModule);
    httpRequestDTO.setReplaceValue(doRequestDTO.getReplaceValue());

    try {
      HttpRequest httpRequest = (HttpRequest) SpringContextUtil.getBean(getHttpRequest(httpApiInfo));

      Response response = httpRequest.request(httpRequestDTO);
      HttpResponseDTO httpResponseDTO = httpRequest.dealResponse(response, doRequestDTO.getStepName());

      BeanUtils.copyProperties(httpResponseDTO, doResponseDTO);
    } catch (BeansException e) {
      log.error("获取bean失败: {}", getHttpRequest(httpApiInfo));
      ResponseUtils.makeApiResponse(doResponseDTO, ResultEnum.SYSTEM_ERROR, "获取bean失败");
    } catch (JSONException e) {
      log.error("json解析异常  ", e.fillInStackTrace());
    } catch (Exception e) {
      log.error("系统异常 ", e.fillInStackTrace());
      ResponseUtils.makeApiResponse(doResponseDTO, ResultEnum.SYSTEM_ERROR);
    }

    return doResponseDTO;
  }

  private String getHttpRequest(HttpApiInfo httpApiInfo) {
    return httpApiInfo.getRequestMethod() + httpApiInfo.getRequestDataType() + "Request";
  }

}
