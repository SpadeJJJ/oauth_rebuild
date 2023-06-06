package com.spade.oauth.context;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class OAuthPathRepository {

    @Value("${oauth2.authorize.callback.urls}")
    private final List<String> paths;

    public List<String> getOAuthPaths() {
        return this.paths;
    }

    public String test() {
        for (String a : getOAuthPaths()) {
            String temp = a.split("/")[1];
            System.out.println(temp);
        }
        return "naver";
    }
}
