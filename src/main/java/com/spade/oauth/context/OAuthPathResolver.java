package com.spade.oauth.context;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class OAuthPathResolver {

    private final OAuthPathRepository oAuthPathRepository;

    public void setMatchers() {
        for(String path : oAuthPathRepository.getOAuthPaths()) {
//            matchers.add(new AntPathMatcher(path));
        }
    }

    public void match(String path) {
//        for(AntPathMatcher matcher : matchers) {
//            if(matcher.match(path))
//        }
    }


}
