package com.arrange.play.dao.mysql.type;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.Feature;
import com.arrange.play.model.entity.inner.ReplaceMethod;
import com.arrange.play.util.StringsUtils;
import com.google.common.collect.Maps;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.springframework.util.CollectionUtils;

/**
 * 数据库字段类型转换
 * json --> Map<String, ReplaceMethod>
 */
public class ReplaceValueMapTypeHandler extends BaseTypeHandler<Map<String, ReplaceMethod>> {

  @Override
  public void setNonNullParameter(PreparedStatement preparedStatement, int i,
      Map<String, ReplaceMethod> stringReplaceMethodMap, JdbcType jdbcType) throws SQLException {
    String content = CollectionUtils.isEmpty(stringReplaceMethodMap) ? null : JSON.toJSONString(stringReplaceMethodMap);
    preparedStatement.setString(i, content);
  }

  @Override
  public Map<String, ReplaceMethod> getNullableResult(ResultSet resultSet, String s)
      throws SQLException {
    return this.getMapByJsonObjectString(resultSet.getString(s));
  }

  @Override
  public Map<String, ReplaceMethod> getNullableResult(ResultSet resultSet, int i)
      throws SQLException {
    return this.getMapByJsonObjectString(resultSet.getString(i));
  }

  @Override
  public Map<String, ReplaceMethod> getNullableResult(CallableStatement callableStatement, int i)
      throws SQLException {
    return this.getMapByJsonObjectString(callableStatement.getString(i));
  }

  private Map<String, ReplaceMethod> getMapByJsonObjectString(String content) {
    return StringsUtils.isEmptyOrNull(content) ? Maps.newHashMap()
        : JSON.parseObject(content, new TypeReference<Map<String, ReplaceMethod>>(){}, Feature.OrderedField);
  }

}
