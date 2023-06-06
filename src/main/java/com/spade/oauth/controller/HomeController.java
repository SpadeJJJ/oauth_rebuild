package com.spade.oauth.controller;

import com.spade.oauth.redis.service.kakao.RedisKakaoOauthStateService;
import com.spade.oauth.redis.service.naver.RedisNaverOauthStateService;
import com.spade.oauth.util.OauthStateUtil;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 테스트용
 * Oauth 요청 Controller
* */
@RequiredArgsConstructor
@RestController
public class HomeController {


    private final RedisNaverOauthStateService redisNaverOauthStateService;
    private final RedisKakaoOauthStateService redisKakaoOauthStateService;
    @Autowired
    private Environment environment;


    @GetMapping("/")
    public String home(Model model, HttpSession session) {

        return "index";
    }

    /*@GetMapping("/naver")
    public String naverTest(Model model, HttpSession session) {
        String state = OauthStateUtil.createState();

        redisNaverOauthStateService.saveOauthState(state, "naver");
        model.addAttribute("responseType", "code");
        model.addAttribute("state", state);
        model.addAttribute("clientId", "UMKT4dIs6FAj23xkYaC1");
        model.addAttribute("redirectUri", "http://localhost:8080/naver/callback");

        return "naver";
    }

    @GetMapping("/kakao")
    public String kakaoTest(Model model, HttpSession session) {
        String state = OauthStateUtil.createState();

        redisKakaoOauthStateService.saveOauthState(state, "kakao");
        model.addAttribute("responseType", "code");
        model.addAttribute("state", state);
        model.addAttribute("clientId", "a6c11d53fee9fbb93f86842dd20f0848");
        model.addAttribute("redirectUri", "http://localhost:8080/kakao/callback");

        return "kakao";
    }*/
    @GetMapping("/{authType}")
    public String oAuthTest(Model model, @PathVariable("authType") String authType) {
        String state = OauthStateUtil.createState();

     /*
     팀장님 코드
     AuthType authType = AuthType.fromString(authType);
        redisOAuthStateService.saveOauthState(state, authType);*/

        redisNaverOauthStateService.saveOauthState(state, "naver");
        model.addAttribute("responseType", "code");
        model.addAttribute("state", state);
        model.addAttribute("clientId", "UMKT4dIs6FAj23xkYaC1");
        model.addAttribute("redirectUri", "http://localhost:8080/naver/callback");

        return "naver";
    }

}
