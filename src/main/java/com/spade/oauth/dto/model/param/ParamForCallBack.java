package com.spade.oauth.dto.model.param;

import feign.form.FormProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestParam;

@Getter
@Setter
public class ParamForCallBack {

    private String state;
    private String code;
    private String error;
    @FormProperty("error_Description")
    private String errorDescription;
}
