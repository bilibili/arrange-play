package com.arrange.play.dao.mysql.mapper;


import com.arrange.play.model.entity.SqlModule;
import java.util.List;

public interface SqlModuleMapper {

  SqlModule selectOneRecordById(Integer id);

  SqlModule selectOneRecordByRecord(SqlModule sqlModule);

  List<SqlModule> selectListRecord(SqlModule sqlModule);

}
