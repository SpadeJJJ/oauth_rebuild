package com.spade.oauth.manager;

import com.spade.oauth.manager.OAuthPropertyManager;
import com.spade.oauth.service.CommonOAuthService;
import com.spade.oauth.service.OAuthService;
import feign.codec.Encoder;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class OAuthServiceManager {
    private Map<String, OAuthService> oAuthServiceMap = new HashMap<>();

    private final OAuthPropertyManager oAuthPropertyManager;
    private final Encoder encoder;

    public OAuthService getOAuthService(String type) {
        if (oAuthServiceMap.containsKey(type)) {
            return oAuthServiceMap.get(type);
        }

        OAuthService oAuthService = new CommonOAuthService(encoder, oAuthPropertyManager.getRequestTokenUrl(type));
        oAuthServiceMap.put(type, oAuthService);

        return oAuthService;
    }
}
