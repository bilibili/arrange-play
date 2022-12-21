package com.arrange.play.dao.mysql.mapper;

import com.arrange.play.model.entity.HttpApiInfo;

public interface HttpApiInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(HttpApiInfo record);

    int insertSelective(HttpApiInfo record);

    HttpApiInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HttpApiInfo record);

    int updateByPrimaryKey(HttpApiInfo record);
}
