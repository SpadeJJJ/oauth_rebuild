package com.spade.oauth;

public enum TestEnum {

    NAVER("naver"),
    KAKAO("kakao");

    private String name;

    private TestEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public static TestEnum getType(String key) {
        for (TestEnum testEnum : values()) {
            if (key.contains(testEnum.getName())) {
                return testEnum;
            }
        }
        return null;
    }
}
