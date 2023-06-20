package com.spade.oauth.chain;

import com.spade.oauth.dto.OAuthResultToken;
import com.spade.oauth.dto.param.ParamForAccessToken;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;

import java.util.Map;

@RequiredArgsConstructor
public class OAuthEventPublisherHandler extends Chain {

    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void request(String type, String result, Map<String, String> map) {
        System.out.println("publish chain ");
        publishEvent(type, result, map.toString());
    }

    public OAuthResultToken publishEvent(String type, String result, String param) {
        OAuthResultToken resultToken = new OAuthResultToken(type, result, param);
        applicationEventPublisher.publishEvent(resultToken);
        return resultToken;
    }

    @Override
    public void request(String type, Map<String, String[]> map) {}

    @Override
    public void request(String type, Map<String, String> map, ParamForAccessToken param) {}
}
