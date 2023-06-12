package com.spade.oauth.dto.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * Event Listener 결과 DTO
 */
@Getter
@Setter
@AllArgsConstructor
public class OAuthResultToken {

    private String result;

    private String callbackUrl;

    private String param;
}
