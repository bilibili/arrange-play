package com.arrange.play.dao.mysql.service;

import com.arrange.play.dao.mysql.mapper.MysqlInfoMapper;
import com.arrange.play.model.entity.MysqlInfo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MysqlInfoService {

  @Autowired
  private MysqlInfoMapper mysqlInfoMapper;

  public MysqlInfo selectOneRecordById(Integer id) {
    return mysqlInfoMapper.selectOneRecordById(id);
  }

  public MysqlInfo selectOneRecordByRecord(MysqlInfo mysqlInfo) {
    return mysqlInfoMapper.selectOneRecordByRecord(mysqlInfo);
  }

  public List<MysqlInfo> selectListRecord(MysqlInfo mysqlInfo) {
    return mysqlInfoMapper.selectListRecord(mysqlInfo);
  }

}
