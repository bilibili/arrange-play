package com.arrange.play.model.entity;

import java.sql.Timestamp;
import java.util.List;
import lombok.Data;

@Data
public class SqlModule {

  private Integer id;

  private Integer mysqlConnectionId;

  private String sqlName;

  private String sqlType;

  private String sqlModuleContent;

  private List<String> paramKeys;

  private String moduleDesc;

  private Timestamp ctime;

  private Timestamp mtime;

}
