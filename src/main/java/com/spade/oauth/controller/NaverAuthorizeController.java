package com.spade.oauth.controller;

import com.spade.oauth.domain.redis.OauthState;
import com.spade.oauth.dto.model.param.StateInfoForParam;
import com.spade.oauth.exception.AuthorizeFailureException;
import com.spade.oauth.redis.service.OauthStateService;
import com.spade.oauth.service.naver.NaverOauthService;
import com.spade.oauth.service.naver.NaverRequestParamCreateService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class NaverAuthorizeController {

    @Autowired
    NaverOauthService naverOauthService;

    @Autowired
    NaverRequestParamCreateService naverRequestParamCreateService;

    @Autowired
    OauthStateService oauthStateService;

    @GetMapping("${oauth2.naver.authorize.request.callback.url}")
    public void getResponseCode(@RequestParam(value = "state") String state,
                                @RequestParam(value = "code") String code) {
        System.out.println("state "+state+" code "+code);
    }

    @GetMapping("/test/callback")
    public String getNaverAuthorizeRequestRollBack(HttpSession session,
                       @RequestParam(value = "state") String responseState,
                       @RequestParam(value = "code", required = false) String code,
                       @RequestParam(value = "error", required = false) String error,
                       @RequestParam(value = "error_description", required = false) String errorDescription) {
        String result = null;

        if(error != null) {
            throw new AuthorizeFailureException(errorDescription);
        }

        if(oauthStateService.checkState(responseState)) {
            System.out.println("state pass");
            result = naverOauthService.requestAuthorizeTokenCreate(naverRequestParamCreateService.createParamForAccessTokenCreate(new StateInfoForParam(code, responseState)));
        } else {
            System.out.println("state fail: "+responseState +", "+ (String)session.getAttribute("state"));
            throw new AuthorizeFailureException("response state is not equals with send state");
        }

        return result;
    }

    @GetMapping("/token/update")
    public String updateNaverAccessToken() {
        String result = null;
        result = naverOauthService.requestAuthorizeTokenUpdate(naverRequestParamCreateService.createParamForAccessTokenUpdate(new StateInfoForParam()));
        return result;
    }
}