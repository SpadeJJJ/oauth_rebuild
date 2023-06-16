package com.spade.oauth.feign.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@FeignClient(value = "client")
public interface OAuthClient {
    @RequestMapping(method=GET)
    String requestAccessTokenCreate();
}
