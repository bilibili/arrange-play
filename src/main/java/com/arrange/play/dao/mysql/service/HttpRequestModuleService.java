package com.arrange.play.dao.mysql.service;


import com.arrange.play.dao.mysql.mapper.HttpRequestModuleMapper;
import com.arrange.play.model.entity.HttpRequestModule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class HttpRequestModuleService {

  @Autowired
  private HttpRequestModuleMapper httpRequestModuleMapper;

  public HttpRequestModule selectRequestModuleByRequestModuleId(Integer requestModuleId) {
    return httpRequestModuleMapper.selectByPrimaryKey(requestModuleId);
  }

  public int insertOneHttpRequestModule(HttpRequestModule httpRequestModule) {
    return httpRequestModuleMapper.insert(httpRequestModule);
  }

}
