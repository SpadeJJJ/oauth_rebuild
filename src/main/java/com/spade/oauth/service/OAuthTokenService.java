package com.spade.oauth.service;

import com.spade.oauth.context.OAuthType;
import com.spade.oauth.domain.redis.OauthState;
import com.spade.oauth.dto.model.param.ParamForAccessToken;
import com.spade.oauth.dto.model.param.ParamForCallBack;
import com.spade.oauth.dto.model.param.ParamForStateInfo;
import com.spade.oauth.exception.AuthorizeFailureException;
import com.spade.oauth.redis.service.RedisOauthStateService;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class OAuthTokenService {
    private ApplicationContext applicationContext;

    public String requestToken(OAuthType oAuthType, ParamForCallBack param) {
        String serviceName = oAuthType.type + "Service";
        String paramServiceName = oAuthType.type+"ParamService";
        String redisServiceName = oAuthType.type+"RedisService";
        String result = "";

        OauthService service = (OauthService)applicationContext.getBean(serviceName);
        RedisOauthStateService redisOauthStateService = (RedisOauthStateService) applicationContext.getBean(redisServiceName);
        RequestParamCreateService paramService = (RequestParamCreateService) applicationContext.getBean(paramServiceName);

        System.out.println("state check "+param.getState() +redisOauthStateService.checkState(param.getState()));

//        if(!redisOauthStateService.checkState(param.getState())) {
//            return "token request fail. not equals state";
//        }

        try {
            ParamForAccessToken token = paramService.createParamForAccessTokenCreate(new ParamForStateInfo(param.getState(), param.getCode()));
            result = service.requestForAuthorizeTokenCreate(token);
        } catch (AuthorizeFailureException e) {
            return e.getMessage();
        } catch (Exception e) {
            return e.getMessage();
        } finally {
            Optional<OauthState> state = redisOauthStateService.findState(param.getState());
            if(state.isPresent()) {
                redisOauthStateService.deleteOauthState(state.get().getId());
            }
        }


        return  result;
    }


}
