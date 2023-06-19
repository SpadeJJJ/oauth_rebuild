package com.spade.oauth;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TestDTO {
    private String callBackUrl;
    private String requestTokenUrl;

    private String clientId;

    private String grantType;

}
