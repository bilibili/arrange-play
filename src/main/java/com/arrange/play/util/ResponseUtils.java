package com.arrange.play.util;

import com.arrange.play.enums.ResultEnum;
import com.arrange.play.model.BaseResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ResponseUtils {

    public static void makeApiResponse(BaseResponse baseResponse, ResultEnum resultEnum) {
        baseResponse.setResponseCode(resultEnum.getErrorCode());
        baseResponse.setResponseMsg(resultEnum.getErrorMsg());
    }

    public static void makeApiResponse(BaseResponse baseResponse, ResultEnum resultEnum, String errorInfo) {
        baseResponse.setResponseCode(resultEnum.getErrorCode());
        baseResponse.setResponseMsg(resultEnum.getErrorMsg() + ": " + errorInfo);
    }

}
