package com.spade.oauth;

import java.util.ArrayList;
import java.util.List;

public enum OAuthCorporationType {
    NAVER("naver", true), KAKAO("kakao", true), GOOGLE("google", true);

    private String type;

    private boolean isLoad = false;

    private boolean isCommon;

    OAuthCorporationType(String type, boolean isCommon) {
        this.type = type;
        this.isCommon = isCommon;
    }

    public void setIsLoad(boolean isLoad) {
        this.isLoad = isLoad;
    }

    public static Boolean isCommonType(String type) {
        if (OAuthCorporationType.valueOf(type.toUpperCase()).isCommon) {
            return true;
        }
        return false;
    }

    public static List<String> getTypes() {
        List<String> types = new ArrayList<>();

        for (OAuthCorporationType type : values()) {
            if (type.isLoad) {
                types.add(type.type);
            }
        }
        return types;
    }
}
