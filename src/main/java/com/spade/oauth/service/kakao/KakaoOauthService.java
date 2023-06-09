package com.spade.oauth.service.kakao;

import com.spade.oauth.domain.redis.OauthState;
import com.spade.oauth.dto.model.param.ParamForAccessToken;
import com.spade.oauth.exception.AuthorizeFailureException;
import com.spade.oauth.fegin.client.KakaoOauthClient;
import com.spade.oauth.redis.service.kakao.RedisKakaoOauthStateService;
import com.spade.oauth.service.OauthService;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

@Service("kakaoService")
@RequiredArgsConstructor
public class KakaoOauthService implements OauthService {

    private final KakaoOauthClient kakaoOauthClient;

    @Override
    public String requestForAuthorizeTokenCreate(ParamForAccessToken param) {
        String result = null;

        result = kakaoOauthClient.requestAccessTokenCreate(param);
        if(result == null) {
            throw new AuthorizeFailureException("kakao access token create fail");
        }

        return result;
    }
}
