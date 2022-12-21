package com.arrange.play.service.node.handler.http;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.arrange.play.enums.AimPlaceEnum;
import com.arrange.play.enums.ResultEnum;
import com.arrange.play.exception.StepParamNullException;
import com.arrange.play.model.context.ResponseContext;
import com.arrange.play.model.entity.inner.ReplaceMethod;
import com.arrange.play.model.step.HttpRequestDTO;
import com.arrange.play.model.step.HttpResponseDTO;
import com.arrange.play.util.CreateDate;
import com.arrange.play.util.ResponseUtils;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;
import org.springframework.util.CollectionUtils;

@Slf4j
public abstract class HttpRequest {

  /**
   * B站错误码，标识请求成功
   */
  private static final String CODE = "code";

  public abstract Response request(HttpRequestDTO httpRequestDTO);

  public void dealParams(HttpRequestDTO httpRequestDTO) {
    if (CollectionUtils.isEmpty(httpRequestDTO.getHttpRequestModule().getParamKeys())) {
      return;
    }

    String params = httpRequestDTO.getHttpRequestModule().getModuleContent();
    String header = httpRequestDTO.getHttpRequestModule().getHeader();

    for (String key : httpRequestDTO.getHttpRequestModule().getParamKeys()) {
      ReplaceMethod value = httpRequestDTO.getReplaceValue().get(key);
      if (value == null) {
        log.info("stepNode: {}, 缺少入参: {}", httpRequestDTO.getStepName(), key);
        throw new StepParamNullException("stepNode:" + httpRequestDTO.getStepName() + "缺少入参: " + key);
      }

      String resultValue = CreateDate.getValue(value);
      if (value.getAim().equals(AimPlaceEnum.PARAM.getAim())) {
        params = params.replaceAll("\\$\\{" + key + "}", resultValue);
        continue;
      }
      header = header.replaceAll("\\$\\{" + key + "}", resultValue);
    }
    httpRequestDTO.getHttpRequestModule().setHeader(header);
    httpRequestDTO.getHttpRequestModule().setModuleContent(params);
  }

  public HttpResponseDTO dealResponse(Response response, String stepName) {
    HttpResponseDTO httpResponseDTO = new HttpResponseDTO();
    if (response == null || !response.isSuccessful()) {
      ResponseUtils.makeApiResponse(httpResponseDTO, ResultEnum.API_REQUEST_ERROR);
      return httpResponseDTO;
    }

    try {
      JSONObject res = JSON.parseObject(response.body().string());
      if (res.getIntValue(CODE) != 0) {
        ResponseUtils.makeApiResponse(httpResponseDTO, ResultEnum.API_RESULT_FAIL);
        return httpResponseDTO;
      }

      ResponseContext.setStepResult(stepName, res);
      ResponseUtils.makeApiResponse(httpResponseDTO, ResultEnum.SUCCESS);
      return httpResponseDTO;
    } catch (IOException e) {
      e.printStackTrace();
      ResponseUtils.makeApiResponse(httpResponseDTO, ResultEnum.SYSTEM_ERROR, "执行到: " + stepName);
      return httpResponseDTO;
    }
  }


}
