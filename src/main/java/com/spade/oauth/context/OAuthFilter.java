package com.spade.oauth.context;

import com.spade.oauth.service.OauthService;

public class OAuthFilter {

    private final OAuthContextService contextService;

    public void run(){
        String pathvar = "KAKAO";

        AuthType type = AuthType.ofType(pathvar);
        OauthService oauthService = contextService.from(pathvar);

        
    }
}
