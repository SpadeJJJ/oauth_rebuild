package com.spade.oauth.controller;

import com.spade.oauth.dto.model.param.ParamForAccessToken;
import com.spade.oauth.dto.model.param.ParamForStateInfo;
import com.spade.oauth.redis.service.RedisOauthStateService;
import com.spade.oauth.redis.service.kakao.RedisKakaoOauthStateService;
import com.spade.oauth.service.OauthService;
import com.spade.oauth.service.RequestParamCreateService;
import com.spade.oauth.service.kakao.KakaoOauthService;
import com.spade.oauth.service.kakao.KakaoRequestParamCreateService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@AllArgsConstructor
public class TestController {
    private Map<String, OauthService> oauthService;

    private Map<String, RequestParamCreateService>  requestParamCreateService;

    private Map<String, RedisOauthStateService> redisOauthStateService;


    @GetMapping("/tt/{a}")
    public String testtest(@PathVariable("a") String a) {
        if(a.equals("kakao2")) {
            for(String key : oauthService.keySet()) {
                if (key.contains("kakao")) {
                    oauthService.get(key).requestForAuthorizeTokenCreate(new ParamForAccessToken());
                }
            }
        }
        return "a";
    }
    public void getName(){}
}
