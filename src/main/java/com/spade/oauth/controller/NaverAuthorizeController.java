package com.spade.oauth.controller;

import com.spade.oauth.dto.model.param.ParamForCallBack;
import com.spade.oauth.dto.model.param.ParamForStateInfo;
import com.spade.oauth.exception.AuthorizeFailureException;
import com.spade.oauth.redis.service.naver.RedisNaverOauthStateService;
import com.spade.oauth.service.naver.NaverOauthService;
import com.spade.oauth.service.naver.NaverRequestParamCreateService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**

*/

@RestController
@Slf4j
public class NaverAuthorizeController {

    @Autowired
    NaverOauthService naverOauthService;

    @Autowired
    NaverRequestParamCreateService naverRequestParamCreateService;

    @Autowired
    RedisNaverOauthStateService redisNaverOauthStateService;

    Logger logger = LoggerFactory.getLogger(this.getClass());

//    @GetMapping("${oauth2.naver.authorize.callback.url}")
    public String getNaverAuthorizeRequestCallBack(ParamForCallBack response) {
        String result = "AuthorizeRequest fail";

        if(response.getError() != null) {
            result+=" "+response.getErrorDescription();
            return result;
        }

        if(redisNaverOauthStateService.checkState(response.getState())) {
            logger.info("naver state check pass: "+response.getState());
            result = naverOauthService.requestForAuthorizeTokenCreate(naverRequestParamCreateService.createParamForAccessTokenCreate(ParamForStateInfo.builder()
                                                                                                                                                      .code(response.getCode())
                                                                                                                                                      .state(response.getState())
                                                                                                                                                      .build()));
        } else {
            result += " "+response.getErrorDescription();
        }

        return result;
    }
}