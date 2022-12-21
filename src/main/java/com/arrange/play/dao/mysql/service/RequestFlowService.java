package com.arrange.play.dao.mysql.service;

import com.arrange.play.dao.mysql.mapper.RequestFlowMapper;
import com.arrange.play.model.entity.RequestFlow;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RequestFlowService {

  @Autowired
  private RequestFlowMapper requestFlowMapper;

  public RequestFlow selectRequestFlowByFlowId(Integer id) {
    return requestFlowMapper.selectByPrimaryKey(id);
  }

  public int insertRequestFlowRecord(RequestFlow requestFlow) {
    return requestFlowMapper.insert(requestFlow);
  }

}
