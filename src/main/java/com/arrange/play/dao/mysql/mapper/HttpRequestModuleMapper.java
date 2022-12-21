package com.arrange.play.dao.mysql.mapper;


import com.arrange.play.model.entity.HttpRequestModule;

public interface HttpRequestModuleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(HttpRequestModule record);

    int insertSelective(HttpRequestModule record);

    HttpRequestModule selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HttpRequestModule record);

    int updateByPrimaryKeyWithBLOBs(HttpRequestModule record);

    int updateByPrimaryKey(HttpRequestModule record);
}
