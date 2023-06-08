package com.spade.oauth.dto.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class OAuthResultToken {

    private String result;

    private String callbackUrl;

    private String param;

}
