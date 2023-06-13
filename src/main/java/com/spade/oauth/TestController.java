package com.spade.oauth;

import com.spade.oauth.redis.service.StateUtilService;
import com.spade.oauth.redis.service.naver.RedisNaverOAuthStateService;
import io.lettuce.core.*;
import io.lettuce.core.api.reactive.RedisServerReactiveCommands;
import io.lettuce.core.protocol.CommandType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.*;

import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.types.RedisClientInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.swing.plaf.nimbus.State;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@EnableConfigurationProperties(TestComponent.class)
public class TestController {

    @Autowired
    private RedisNaverOAuthStateService redisNaverService;

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private TestComponent testComponent;

    @Autowired
    Environment environment;

    @GetMapping("/test")
    public String test(){
        try {
            RedisServer redisServer = new RedisServer("localhost", 8080);


//            for(String a : environment.getProperty("naver")) {
//                System.out.println(a);
//            }

            System.out.println(testComponent.getPref().keySet());

            Pattern pattern = Pattern.compile("oauth2.[.+].authorize.callback.url");
            Matcher matcher = pattern.matcher("naver");
            System.out.println(environment.getRequiredProperty("naver"));
            environment.getActiveProfiles();
//            redisNaverService.findState("111111111111");
        } catch (Exception e) {



            System.out.println(e.getClass());
            System.out.println(e.getMessage());
        }
        return "";
    }
}
