package com.arrange.play.dao.mysql.type;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

public class JsonTypeHandler<T> extends BaseTypeHandler<T> {

  private final Class<T> clazz;


  public JsonTypeHandler(Class<T> clazz) {
    if (clazz == null) {
      throw new IllegalArgumentException("Type argument cannot be null");
    }
    this.clazz = clazz;
  }

  @Override
  public void setNonNullParameter(PreparedStatement preparedStatement, int i, T t,
      JdbcType jdbcType) throws SQLException {
    preparedStatement.setString(i, this.toJson(t));
  }

  @Override
  public T getNullableResult(ResultSet resultSet, String s) throws SQLException {
    return this.toObject(resultSet.getString(s), clazz);
  }

  @Override
  public T getNullableResult(ResultSet resultSet, int i) throws SQLException {
    return this.toObject(resultSet.getString(i), clazz);
  }

  @Override
  public T getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
    return this.toObject(callableStatement.getString(i), clazz);
  }

  private String toJson(T object) {
    return JSON.toJSONString(object);
  }

  private T toObject(String content, Class<?> clazz) {
    if (content != null && !content.isEmpty()) {
        return (T) JSON.parseObject(content, clazz, Feature.IgnoreAutoType);
    }
    return null;
  }
}
