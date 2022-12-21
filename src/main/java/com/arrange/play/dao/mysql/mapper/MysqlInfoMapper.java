package com.arrange.play.dao.mysql.mapper;

import com.arrange.play.model.entity.MysqlInfo;
import java.util.List;

public interface MysqlInfoMapper {

  MysqlInfo selectOneRecordById(Integer id);

  MysqlInfo selectOneRecordByRecord(MysqlInfo mysqlInfo);

  List<MysqlInfo> selectListRecord(MysqlInfo mysqlInfo);

}
