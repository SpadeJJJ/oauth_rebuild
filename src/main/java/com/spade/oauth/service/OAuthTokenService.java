package com.spade.oauth.service;

import com.spade.oauth.context.OAuthType;
import com.spade.oauth.context.StateContext;
import com.spade.oauth.domain.redis.OAuthState;
import com.spade.oauth.dto.model.param.ParamForAccessToken;
import com.spade.oauth.dto.model.param.ParamForCallBack;
import com.spade.oauth.dto.model.param.ParamForOAuthAuthorizeRequest;
import com.spade.oauth.exception.AuthorizeFailureException;
import com.spade.oauth.redis.service.RedisOAuthStateService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OAuthTokenService {
    private final ApplicationContext applicationContext;

    private final StateContext stateContext;

    @Value("${spring.data.redis.repositories.enabled}")
    private boolean redisUse = true;

    public boolean getUse() {
        return this.redisUse;
    }

    public String requestToken(OAuthType oAuthType, ParamForCallBack param) {
        String serviceName = oAuthType.type + "Service";
        String paramServiceName = oAuthType.type+"ParamService";
        String redisServiceName = oAuthType.type+"RedisService";
        String result = "";

        OAuthService service = (OAuthService)applicationContext.getBean(serviceName);

        RequestParamCreateService paramService = (RequestParamCreateService) applicationContext.getBean(paramServiceName);

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
            ParamForAccessToken token = paramService.createParamForAccessTokenCreate(new ParamForOAuthAuthorizeRequest(param.getState(), param.getCode()));
            result = service.requestForAuthorizeTokenCreate(token);
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
}
