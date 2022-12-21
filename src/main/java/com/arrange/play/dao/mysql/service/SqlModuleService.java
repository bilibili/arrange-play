package com.arrange.play.dao.mysql.service;

import com.arrange.play.dao.mysql.mapper.SqlModuleMapper;
import com.arrange.play.model.entity.SqlModule;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SqlModuleService {

  @Autowired
  private SqlModuleMapper sqlModuleMapper;

  public SqlModule selectOneRecordById(Integer id) {
    return sqlModuleMapper.selectOneRecordById(id);
  }

  public SqlModule selectOneRecordByRecord(SqlModule sqlModule) {
    return sqlModuleMapper.selectOneRecordByRecord(sqlModule);
  }

  public List<SqlModule> selectListRecord(SqlModule sqlModule) {
    return sqlModuleMapper.selectListRecord(sqlModule);
  }

}
