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

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Request Result :");
        sb.append(result);
        sb.append(",");
        sb.append("url : ");
        sb.append(callbackUrl);
        sb.append(",");
        sb.append("param : ");
        sb.append(param);
        return sb.toString();
    }

}
