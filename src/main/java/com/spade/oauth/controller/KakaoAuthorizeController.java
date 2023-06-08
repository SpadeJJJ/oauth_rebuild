package com.spade.oauth.controller;

import com.spade.oauth.dto.model.param.ParamForCallBack;
import com.spade.oauth.dto.model.param.ParamForStateInfo;
import com.spade.oauth.exception.AuthorizeFailureException;
import com.spade.oauth.redis.service.kakao.RedisKakaoOauthStateService;
import com.spade.oauth.service.kakao.KakaoOauthService;
import com.spade.oauth.service.kakao.KakaoRequestParamCreateService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class KakaoAuthorizeController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    KakaoOauthService kakaoOauthService;

    @Autowired
    KakaoRequestParamCreateService kakaoRequestParamCreateService;

    @Autowired
    RedisKakaoOauthStateService redisKakaoOauthStateService;

//    @GetMapping("${oauth2.kakao.authorize.callback.url}")
    public String getKakaoAuthorizeRequestCallBack(ParamForCallBack response) {
        String result = "AuthorizeRequest fail";

        if(response.getError() != null) {
            result += " "+response.getErrorDescription();
            return result;
        }

        if(redisKakaoOauthStateService.checkState(response.getState())) {
            logger.info("[kakao] state check pass: "+response.getState());
            result = kakaoOauthService.requestForAuthorizeTokenCreate(kakaoRequestParamCreateService.createParamForAccessTokenCreate(ParamForStateInfo.builder()
                                                                                                                                                      .code(response.getCode())
                                                                                                                                                      .state(response.getState())
                                                                                                                                                      .build()));
        } else {
            result += " "+response.getErrorDescription();
        }

        return result;
    }
}
