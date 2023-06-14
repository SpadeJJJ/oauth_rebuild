package com.spade.oauth;

import com.spade.oauth.context.OAuthPathContext;
import com.spade.oauth.redis.service.naver.RedisNaverOAuthStateService;

import jakarta.annotation.Resource;
import jakarta.annotation.Resources;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.*;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
public class TestController {
   @Resource(name = "properties")
    private Map<String, String> properties;

    @GetMapping("/test")
    public String test(){


        System.out.println("=====================================================");
        System.out.println("key and value");
        for (String key : properties.keySet()) {
            System.out.println(key + "  :  "+properties.get(key));
        }
        System.out.println("=====================================================");


        /** callback 가정. */
        String callback = "/naver/callback";

        /** properties에 모든 key value를 받음. */
        for (String key : properties.keySet()) {

            /** callback url과 같은 경우 == /naver/callback  */
            if (callback.equals(properties.get(key))) {

                /**
                 * enum 값들 중에서 name과 key를 비교해서
                 * key에 name이 contains 해서 값이 있으면 리턴
                 * ( key = oauth2.authorize.naver.call-back-url && name = {naver, kakao, ...} )
                 */
                TestEnum result = TestEnum.getType(key);
                System.out.println("result : "+result.getName());
            }
        }

        return "";
    }
}
