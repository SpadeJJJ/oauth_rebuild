package com.spade.oauth.context;

import com.spade.oauth.dto.model.param.ParamForCallBack;

public enum OAuthType {
    NAVER("naver", "naverService"), KAKAO("kakao", "kakaoService");

    public String type;

    public String serviceBeanName;


    OAuthType(String type, String serviceBeanName) {
        this.type = type;
        this.serviceBeanName = serviceBeanName;
    }
}
