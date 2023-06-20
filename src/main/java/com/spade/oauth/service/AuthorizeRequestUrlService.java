package com.spade.oauth.service;

import com.spade.oauth.manager.OAuthPropertyManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigInteger;
import java.security.SecureRandom;

@Service
@RequiredArgsConstructor
public class AuthorizeRequestUrlService {

    private final OAuthPropertyManager oAuthPropertyManager;
    public String createAuthorizeRequestUrlService(String type) {
        UriComponents url = UriComponentsBuilder.newInstance()
                                                .path(oAuthPropertyManager.getRequestTokenUrl(type))
                                                .queryParam("response_type", "code")
                                                .queryParam("client_id", oAuthPropertyManager.getProperty(type).getClientId())
                                                .queryParam("redirect_uri", oAuthPropertyManager.getProperty(type).getCallBackHost()+oAuthPropertyManager.getProperty(type).getCallBackUri())
                                                .queryParam("state", createRandomState())
                                                .build();

        return url.toString();
    }

    public String createRandomState() {
        SecureRandom random = new SecureRandom();
        String state = new BigInteger(130, random).toString();

        return state;
    }
}
