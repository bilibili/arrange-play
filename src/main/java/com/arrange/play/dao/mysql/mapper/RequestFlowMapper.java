package com.arrange.play.dao.mysql.mapper;


import com.arrange.play.model.entity.RequestFlow;

public interface RequestFlowMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RequestFlow record);

    int insertSelective(RequestFlow record);

    RequestFlow selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RequestFlow record);

    int updateByPrimaryKey(RequestFlow record);
}
