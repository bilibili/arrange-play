package com.arrange.play.model.entity;

import java.sql.Timestamp;
import lombok.Data;

@Data
public class MysqlInfo {

  private Integer id;

  private String driverName;

  private String url;

  private String userName;

  private String password;

  private Timestamp ctime;

  private Timestamp mtime;

}
