package com.spade.oauth.domain.redis;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@RedisHash("oauthState")
@Getter
@Setter
public class OAuthState {

    @Id
    private Long id;

    @Indexed
    private String state;

    @Indexed
    private String type;


}
