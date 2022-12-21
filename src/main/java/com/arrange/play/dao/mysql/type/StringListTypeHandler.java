package com.arrange.play.dao.mysql.type;

import com.arrange.play.util.StringsUtils;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.springframework.util.CollectionUtils;

/**
 * 数据库字段类型转换
 * json --> List<String> 以','分隔
 */
public class StringListTypeHandler extends BaseTypeHandler<List<String>> {

  @Override
  public void setNonNullParameter(PreparedStatement preparedStatement, int i, List<String> list,
      JdbcType jdbcType) throws SQLException {
    String content = CollectionUtils.isEmpty(list) ? null : Joiner.on(",").skipNulls().join(list);
    preparedStatement.setString(i, content);
  }

  @Override
  public List<String> getNullableResult(ResultSet resultSet, String s) throws SQLException {
    return this.stringToList(resultSet.getString(s));
  }

  @Override
  public List<String> getNullableResult(ResultSet resultSet, int i) throws SQLException {
    return this.stringToList(resultSet.getString(i));
  }

  @Override
  public List<String> getNullableResult(CallableStatement callableStatement, int i)
      throws SQLException {
    return this.stringToList(callableStatement.getString(i));
  }
  private List<String> stringToList(String str) {
    return StringsUtils.isEmptyOrNull(str) ? Lists.newArrayList() : Splitter.on(",").splitToList(str);
  }
}
