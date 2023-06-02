package com.spade.oauth.redis;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RedisInfo {

    private String refreshToken;
    private String type;
    private String clientId;

    private String state;

    private int expiredTime;
}
