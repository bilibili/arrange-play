package com.arrange.play.model.entity.inner;

import lombok.Data;

/**
 * 取值方案
 */
@Data
public class ReplaceMethod {

  // 暂时没地方用
  //private String name;

  /**
   * local 取value
   * 其他调用方法
   */
  private String from;

  /**
   * param || header
   */
  private String aim;

  /**
   * 取数入参
   */
  private String value;

}
