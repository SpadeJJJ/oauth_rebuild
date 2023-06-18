package com.spade.oauth.service;

import com.spade.oauth.FooController;
import com.spade.oauth.context.OAuthPathContext;
import com.spade.oauth.context.StateContext;
import com.spade.oauth.domain.redis.OAuthState;
import com.spade.oauth.dto.model.OAuthResultToken;
import com.spade.oauth.dto.model.param.ParamForAccessToken;
import com.spade.oauth.dto.model.param.ParamForCallBack;
import com.spade.oauth.exception.AuthorizeFailureException;
import com.spade.oauth.redis.service.RedisOAuthStateService;
import com.spade.oauth.util.ParamCreateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OAuthTokenService {
    private final ApplicationContext applicationContext;

    private final StateContext stateContext;

    private final ApplicationEventPublisher applicationEventPublisher;

    private final OAuthPathContext oAuthPathContext;

    @Value("${spring.data.redis.repositories.enabled}")
    private boolean redisUse = true;

    public boolean getUse() {
        return this.redisUse;
    }

    public String requestToken(String type, ParamForCallBack param) {
        String serviceName = type + "Service";
        String paramServiceName = type + "ParamService";
        String redisServiceName = type + "RedisService";
        String result = "";

        OAuthService service = (OAuthService)applicationContext.getBean(serviceName);

        if (stateContext.checkStateUse()) {
            if (redisUse) {
                System.out.println("use redis");
                RedisOAuthStateService redisOauthStateService = (RedisOAuthStateService) applicationContext.getBean(redisServiceName);
                if (!redisOauthStateService.checkState(param.getState())) {
                    return "token request fail. not equals state";
                }
            } else {
                System.out.println("not use redis");
                if(!stateContext.checkState(param.getState())) {
                    return "token request fail. not equals state";
                }
            }
        }

        try {
            ParamForAccessToken accessTokenParam = new ParamForAccessToken();
            accessTokenParam.setCode(param.getCode());
            accessTokenParam.setState(param.getState());
            ParamCreateUtil.createAccessTokenParam(type, accessTokenParam, oAuthPathContext);

            String url = ParamCreateUtil.getUrlForAccessToken(type, oAuthPathContext);
            result = service.requestForAuthorizeTokenCreate(accessTokenParam,  url);
            System.out.println("result ? "+result);
        } catch (AuthorizeFailureException e) {
            return e.getMessage();
        } catch (Exception e) {
            return e.getMessage();
        } finally {
            if(redisUse) {
                RedisOAuthStateService redisOauthStateService = (RedisOAuthStateService) applicationContext.getBean(redisServiceName);
                Optional<OAuthState> state = redisOauthStateService.findState(param.getState());
                if(state.isPresent()) {
                    redisOauthStateService.deleteOauthState(state.get().getId());
                }
            }

            if(stateContext.checkState(param.getState())) {
                stateContext.deleteState(param.getState());
            }

        }

        return  result;
    }

    public OAuthResultToken send(String result, String url, String param) {
        System.out.println("will send "+result);
        OAuthResultToken resultToken = new OAuthResultToken(result, url, param);
        applicationEventPublisher.publishEvent(resultToken);
        return resultToken;
    }
}
