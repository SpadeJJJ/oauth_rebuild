package com.spade.oauth.service.kakao;

import com.spade.oauth.domain.redis.OauthState;
import com.spade.oauth.dto.model.param.ParamForAccessToken;
import com.spade.oauth.exception.AuthorizeFailureException;
import com.spade.oauth.fegin.client.KakaoOauthClient;
import com.spade.oauth.redis.service.kakao.RedisKakaoOauthStateService;
import com.spade.oauth.service.OauthService;
import com.spade.oauth.service.type.OAuthServiceManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

@Service
public class KakaoOauthService implements OauthService {

    @Autowired
    KakaoOauthClient kakaoOauthClient;

    @Autowired
    RedisKakaoOauthStateService redisKakaoOauthStateService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public OauthService from(String type) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        return OAuthServiceManager.KAKAO.getService().getDeclaredConstructor().newInstance();
    }

    @Override
    public String requestForAuthorizeTokenCreate(ParamForAccessToken param) {
        String result = null;

        try {
            result = kakaoOauthClient.requestAccessTokenCreate(param);
            if(result == null) {
                logger.warn("delete state fail");
                throw new AuthorizeFailureException("kakao access token create fail");
            }
        } catch (AuthorizeFailureException e) {
            logger.warn(e.getMessage());
        } catch (Exception e) {
            logger.warn(e.getMessage());
        } finally {
            Optional<OauthState> state = redisKakaoOauthStateService.findState(param.getState());
            if(state.isPresent()) {
                logger.info("kakao delete state in redis "+state.get().getId());
                redisKakaoOauthStateService.deleteOauthState(state.get().getId());
            }

        }
        return result;
    }
}
