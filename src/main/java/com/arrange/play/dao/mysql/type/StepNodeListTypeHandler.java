package com.arrange.play.dao.mysql.type;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.Feature;
import com.arrange.play.model.entity.inner.StepNode;
import com.arrange.play.util.StringsUtils;
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
 * json --> List<StepNode>
 */
public class StepNodeListTypeHandler extends BaseTypeHandler<List<StepNode>> {


  @Override
  public void setNonNullParameter(PreparedStatement preparedStatement, int i, List<StepNode> ts,
      JdbcType jdbcType) throws SQLException {
    String content = CollectionUtils.isEmpty(ts) ? null : JSON.toJSONString(ts);
    preparedStatement.setString(i, content);
  }

  @Override
  public List<StepNode> getNullableResult(ResultSet resultSet, String s) throws SQLException {
    return this.getListByJsonArrayString(resultSet.getString(s));
  }

  @Override
  public List<StepNode> getNullableResult(ResultSet resultSet, int i) throws SQLException {
    return this.getListByJsonArrayString(resultSet.getString(i));
  }

  @Override
  public List<StepNode> getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
    return this.getListByJsonArrayString(callableStatement.getString(i));
  }

  private List<StepNode> getListByJsonArrayString(String content) {
    return StringsUtils.isEmptyOrNull(content) ? Lists.newArrayList()
        : JSON.parseObject(content, new TypeReference<List<StepNode>>(){}, Feature.OrderedField);
  }

}
