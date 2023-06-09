package com.spade.oauth.service.naver;

import com.spade.oauth.domain.redis.OauthState;
import com.spade.oauth.dto.model.param.ParamForAccessToken;
import com.spade.oauth.exception.AuthorizeFailureException;
import com.spade.oauth.fegin.client.NaverOauthClient;
import com.spade.oauth.redis.service.naver.RedisNaverOauthStateService;
import com.spade.oauth.service.OauthService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

@Service("naverService")
@RequiredArgsConstructor
public class NaverOauthService implements OauthService {

    private final NaverOauthClient naverOauthClient;

    @Override
    public String requestForAuthorizeTokenCreate(ParamForAccessToken param) {
        String result = null;

        result = naverOauthClient.requestAccessTokenCreate(param);
        if(result == null) {
            throw new AuthorizeFailureException("naver access token create fail");
        }

        return result;
    }
}