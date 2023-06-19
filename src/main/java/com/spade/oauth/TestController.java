package com.spade.oauth;

import com.spade.oauth.context.OAuthPathContext;

import com.spade.oauth.dto.OAuthResultToken;
import com.spade.oauth.property.NaverOAuthProperty;
import com.spade.oauth.property.PropertiesResourceConfig;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final OAuthPathContext oAuthPathContext;

    private final NaverOAuthProperty naverOAuthProperty;
    @GetMapping("/naver")
    public String test(){
        String url = "https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=UMKT4dIs6FAj23xkYaC1&redirect_uri=http://localhost:8080/naver/callback&state=1111111111111";
        return url;
    }

    @GetMapping("/kakao")
    public String test2(){
        String url = "https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=a6c11d53fee9fbb93f86842dd20f0848&redirect_uri=http://localhost:8080/kakao/callback";
        return url;
    }

    @GetMapping("/test")
    public String test3() {

        for (String a :  oAuthPathContext.getOAuthPathMap().keySet().stream().toList()) {
            System.out.println(a);
        }

        return "";
    }
}
