package com.arrange.play.dao.mysql.con;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.arrange.play.model.entity.MysqlInfo;
import com.arrange.play.util.StringsUtils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Connector {

  public static Connection getConn(MysqlInfo mysqlInfo) {
    Connection conn = null;
    try {
      Class.forName(mysqlInfo.getDriverName());
      conn = DriverManager.getConnection(mysqlInfo.getUrl(), mysqlInfo.getUserName(), mysqlInfo.getPassword());
    } catch (ClassNotFoundException | SQLException e) {
      log.info("数据库连接失败: {}", JSON.toJSONString(mysqlInfo), e.fillInStackTrace());
    }

    return conn;
  }

  public static JSONArray selectRecord(Connection conn, String sql) {
    if (conn == null || StringsUtils.isEmptyOrNull(sql)) {
      return null;
    }
    Statement stmt = null;
    JSONArray result = new JSONArray(new LinkedList<>());

    try {
      stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery(sql);
      ResultSetMetaData rsmd = rs.getMetaData();
      int numColumns = rsmd.getColumnCount();
      while(rs.next()) {
        log.info("{}", rs);
        JSONObject obj = new JSONObject(true);
        for (int index = 1; index <= numColumns; index++) {
          String value = rs.getString(index);
          if (StringsUtils.isNotEmptyOrNull(value)) {
            obj.put(rsmd.getColumnName(index),  value);
          }
        }
        result.add(obj);
      }

      rs.close();
      stmt.close();
      conn.close();
    } catch (SQLException throwables) {
      throwables.printStackTrace();
      return null;
    }

    return result;
  }

  public static int insertOneRecord(Connection conn, String sql) {
    int sign = 0;
    if (conn == null || StringsUtils.isEmptyOrNull(sql)) {
      return sign;
    }
    Statement stmt = null;

    try {
      stmt = conn.createStatement();
      sign = stmt.executeUpdate(sql);
      conn.close();
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    } finally {
      try {
        if (stmt != null) {
          stmt.close();
        }
      } catch (SQLException throwables) {
        throwables.printStackTrace();
      }
    }
    return sign;
  }

  public static int updateRecord(Connection conn, String sql) {
    int sign = 0;
    if (conn == null || StringsUtils.isEmptyOrNull(sql)) {
      return sign;
    }
    Statement stmt = null;

    try {
      stmt = conn.createStatement();
      sign = stmt.executeUpdate(sql);
      conn.close();
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    } finally {
      try {
        if (stmt != null) {
          stmt.close();
        }
      } catch (SQLException throwables) {
        throwables.printStackTrace();
      }
    }
    return sign;
  }

}
