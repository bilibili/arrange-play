package com.arrange.play.model.entity.inner;

import lombok.Data;

/**
 * 节点属性
 */
@Data
public class StepNode {

  /**
   * 节点名称
    */
  private String stepName;

  /**
   * 节点类型
   * Http
   * Grpc
   * Mysql
   * Redis
   * Databus
   * Railgun
   */
  private String stepType;

  /**
   * 对应请求类型 stepType 下
   * 请求编号id
   */
  private Integer apiId;

  /**
   * 对应请求类型 stepType 下
   * 请求模板id
   */
  private Integer requestModuleId;

  /**
   * 等待时间 兼容异步接口
    */
  private long waitTime;

}
