package com.spade.oauth;

import com.spade.oauth.context.OAuthPathContext;

import com.spade.oauth.property.NaverOAuthProperty;
import com.spade.oauth.service.OAuthService;
import com.spade.oauth.service.naver.NaverOAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Method;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final OAuthPathContext oAuthPathContext;

//    private final NaverOAuthProperty naverOAuthProperty;

    private final Environment environment;

    private final ApplicationContext applicationContext;

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


        for (String a : applicationContext.getBeanNamesForType(OAuthService.class)) {
            System.out.println(a);
//            System.out.println(((OAuthService)applicationContext.getBean(a)).getoAuthProperty().getCallBackUri());
        }

        System.out.println();
        for (Method a : NaverOAuthService.class.getDeclaredMethods()) {
            System.out.println(a.getName());
        }



//        for (String a :  oAuthPathContext.getOAuthPathMap().keySet().stream().toList()) {
//            System.out.println(a);
//        }

        return "";
    }
}
