package com.spade.oauth.controller;

import com.spade.oauth.redis.service.kakao.RedisKakaoOauthStateService;
import com.spade.oauth.redis.service.naver.RedisNaverOauthStateService;
import com.spade.oauth.util.OauthStateUtil;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
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
@Controller
public class HomeController {

    @GetMapping("${oauth2.authorize.redirect.url}")
    public String home(HttpServletResponse response) {
        String result = response.getHeader("result");
        return result;
    }
}
