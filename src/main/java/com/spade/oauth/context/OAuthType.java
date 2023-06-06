package com.spade.oauth.context;

public enum OAuthType {
    NAVER("naver"), KAKAO("kakao");

    public String type;

    OAuthType(String type) {
        this.type = type;
    }


}
