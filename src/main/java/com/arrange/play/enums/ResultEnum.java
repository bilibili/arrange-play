package com.arrange.play.enums;

public enum ResultEnum {

    /**
     * 接口成功返回
     * */
    SUCCESS("000000", "成功"),

    /**
     * 请求在队列中处理
     * */
    PENDING("000001", "等待定时任务处理的记录"),

    /**
     * 参数异常返回
     * */
    PARAM_NULL("100001", "请求参数为空"),

    /**
     * 重复请求
     * */
    DUPLICATE_REQUEST("100002", "已有正在处理的请求"),

    /**
     * 没有找到对应的handler
     * */
    OUT_RANGE_REQUEST("100003", "没有找到对应的处理方法"),

    /**
     * 无结果数据请求
     * */
    NO_DATA("100004", "数据库无数据"),

    /**
     * 参数不合法
     * */
    UN_LAW("100005", "参数不合法"),

    /**
     * 接口调用失败
     * */
    API_REQUEST_ERROR("100006", "外部接口调用异常"),

    /**
     * 对象转换失败
     * */
    API_RESULT_FAIL("100007", "接口返回失败"),

    /**
     * sql异常
     * */
    SQL_ERROR("100008", "数据库异常"),

    /**
     * http链接失败
     * */
    API_CONNECT_ERROR("100009", "外部接口调用异常"),

    /**
     *
     */
    FLOW_STEP_FAIL("200001", "步骤执行失败"),

    /**
     * 系统异常返回, 用于接口全局Exception返回码
     * */
    SYSTEM_ERROR("999999", "系统异常");

    private String errorCode;

    private String errorMsg;

    ResultEnum(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

}
