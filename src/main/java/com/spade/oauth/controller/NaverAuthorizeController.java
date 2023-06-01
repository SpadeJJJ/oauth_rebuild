package com.spade.oauth.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.google.gson.Gson;
import com.spade.oauth.exception.AuthorizeFailureException;
import com.spade.oauth.fegin.Test;
import com.spade.oauth.service.naver.NaverOauthService;
import com.spade.oauth.service.naver.NaverRequestParamCreateService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.HashMap;

@Controller
public class NaverAuthorizeController {

    @Autowired
    NaverOauthService naverOauthService;

    @Autowired
    NaverRequestParamCreateService naverRequestParamCreateService;

    @GetMapping("${oauth2.naver.authorize.request.callback.url}")
    public void getResponseCode(@RequestParam(value = "state") String state,
                                @RequestParam(value = "code") String code) {
        System.out.println("state "+state+" code "+code);
    }

    @GetMapping("/test/callback")
    @ResponseBody
    public String test(HttpSession session,
                       @RequestParam(value = "state") String responseState,
                       @RequestParam(value = "code", required = false) String code,
                       @RequestParam(value = "error", required = false) String error,
                       @RequestParam(value = "error_description", required = false) String errorDescription) {
        Gson result = null;

        if(error != null) {
            throw new AuthorizeFailureException(errorDescription);
        }


        System.out.println("first code : "+code);
        if(responseState.equals((String)session.getAttribute("state"))) {
            result = naverOauthService.requestAuthorizeTokenCreate(naverRequestParamCreateService.createParamForAccessTokenCreate(code, responseState));
            System.out.println(" ?? "+result);
        } else {
            System.out.println("fail = "+responseState +" vs "+ (String)session.getAttribute("state"));
            throw new AuthorizeFailureException("response state is not equals with send state");
        }

        return "jhi";
    }

//    @GetMapping("test")
//    public String test1() {
////        String result = naverOauthService.requestAuthorize(createTestParamMap());
////        String result = naverOauthService.requestAuthorize("code", "UMKT4dIs6FAj23xkYaC1", "http://localhost:8080/test/callback", new BigInteger(130, random).toString());
//        System.out.println(result);
//        return "redirect:"+result;
//    }

    HashMap<String, Object> createTestParamMap() {
        SecureRandom random = new SecureRandom();
        HashMap<String, Object> map = new HashMap<>();
        map.put("responseType", "code");
        map.put("clientId", "UMKT4dIs6FAj23xkYaC1");
        map.put("redirectUri", "http://localhost:8080/test/callback");
        map.put("state", new BigInteger(130, random).toString());

        return map;
    }
}
