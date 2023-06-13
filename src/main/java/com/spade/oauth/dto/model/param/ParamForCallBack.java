package com.spade.oauth.dto.model.param;

import feign.form.FormProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * OAuth 인증 CallBack 관련 파라미터 정보 토큰
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
