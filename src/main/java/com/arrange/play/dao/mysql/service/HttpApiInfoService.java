package com.arrange.play.dao.mysql.service;

import com.arrange.play.dao.mysql.mapper.HttpApiInfoMapper;
import com.arrange.play.model.entity.HttpApiInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class HttpApiInfoService {

  @Autowired
  private HttpApiInfoMapper httpApiInfoMapper;

  public HttpApiInfo selectApiInfoByApiId(int apiId) {
    return httpApiInfoMapper.selectByPrimaryKey(apiId);
  }

}
