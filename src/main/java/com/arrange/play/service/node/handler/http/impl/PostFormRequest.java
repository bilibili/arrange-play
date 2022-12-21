package com.arrange.play.service.node.handler.http.impl;

import com.alibaba.fastjson.JSON;
import com.arrange.play.model.step.HttpRequestDTO;
import com.arrange.play.service.node.handler.http.HttpRequest;
import com.arrange.play.util.HttpRequestUtil;
import com.arrange.play.util.TransUtils;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PostFormRequest extends HttpRequest {

  @Override
  public Response request(HttpRequestDTO httpRequestDTO) {
    String url = httpRequestDTO.getHttpApiInfo().getApiDomain() + httpRequestDTO.getHttpApiInfo().getApiUri();
    dealParams(httpRequestDTO);

    return HttpRequestUtil.formPostRequest(url,
        TransUtils
            .jsonToMap(JSON.parseObject(httpRequestDTO.getHttpRequestModule().getModuleContent())),
        TransUtils.jsonToMap(JSON.parseObject(httpRequestDTO.getHttpRequestModule().getHeader())));
  }

}
