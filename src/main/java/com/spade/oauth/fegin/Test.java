package com.spade.oauth.fegin;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


public interface Test {

    String requestAuthorize(@RequestParam(value = "response_type") String responseType,
                          @RequestParam(value = "client_id") String clientId,
                          @RequestParam(value = "redirect_uri") String redirectUri,
                          @RequestParam(value = "state") String state);


}
