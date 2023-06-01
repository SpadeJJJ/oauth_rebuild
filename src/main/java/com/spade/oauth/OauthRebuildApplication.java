package com.spade.oauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class OauthRebuildApplication {

    public static void main(String[] args) {
        SpringApplication.run(OauthRebuildApplication.class, args);
    }

}
