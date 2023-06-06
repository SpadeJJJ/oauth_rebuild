package com.spade.oauth.service.naver;

import com.spade.oauth.domain.redis.OauthState;
import com.spade.oauth.dto.model.param.ParamForAccessToken;
import com.spade.oauth.exception.AuthorizeFailureException;
import com.spade.oauth.fegin.client.NaverOauthClient;
import com.spade.oauth.redis.service.naver.RedisNaverOauthStateService;
import com.spade.oauth.service.OauthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

@Service(value = "naver")
public class NaverOauthService implements OauthService {

    @Autowired
    private NaverOauthClient naverOauthClient;

    @Autowired
    private RedisNaverOauthStateService redisNaverOauthStateService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public OauthService from(String type) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        return null;
    }

    @Override
    public String requestForAuthorizeTokenCreate(ParamForAccessToken param) {
        String result = null;

        try {
            result = naverOauthClient.requestAccessTokenCreate(param);
            if(result == null) {
                throw new AuthorizeFailureException("naver access token create fail");
            }
        } catch (AuthorizeFailureException e) {
            logger.warn(e.getMessage());
        } catch (Exception e) {
            logger.warn(e.getMessage());
        } finally {
            Optional<OauthState> state = redisNaverOauthStateService.findState(param.getState());
            if(state.isPresent()) {
                logger.info("naver delete state in redis "+state.get().getId());
                redisNaverOauthStateService.deleteOauthState(state.get().getId());
            }
        }
        return result;
    }
}