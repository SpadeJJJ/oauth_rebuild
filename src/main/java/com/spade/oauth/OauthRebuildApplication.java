package com.spade.oauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(exclude = {
        RedisAutoConfiguration.class, RedisRepositoriesAutoConfiguration.class
})
@EnableFeignClients
public class OauthRebuildApplication {

    public static void main(String[] args) {
        SpringApplication.run(OauthRebuildApplication.class, args);
    }

}
