package com.spade.oauth.dto.param;

import feign.form.FormProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Authorize 요청 후 호출된 callback url의 param 정보
 */
@Getter
@Setter
public class ParamForCallBack {

    private String state;
    private String code;
    private String error;
    @FormProperty("error_Description")
    private String errorDescription;
}
