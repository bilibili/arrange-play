package com.arrange.play.model.entity;

import lombok.Data;

/**
 * http接口信息
 */
@Data
public class HttpApiInfo {

    /**
     * 接口编号
     */
    private Integer id;

    /**
     * 接口所属业务线id
     */
    private Integer busiId;

    /**
     * 接口名
     */
    private String apiName;

    /**
     * 域名 / ip + port
     */
    private String apiDomain;

    /**
     * 接口路由
     */
    private String apiUri;

    /**
     * 接口请求方式
     * get
     * post
     */
    private String requestMethod;

    /**
     * 参数类型
     * Form
     * Post
     * Uri
     */
    private String requestDataType;


}
