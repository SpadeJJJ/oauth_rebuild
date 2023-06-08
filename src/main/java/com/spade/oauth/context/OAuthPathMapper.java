package com.spade.oauth.context;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.spade.oauth.dto.model.param.ParamForCallBack;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.AntPathRequestMatcherProvider;
import org.springframework.boot.autoconfigure.security.servlet.RequestMatcherProvider;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.HashMap;
import java.util.Map;

@Component
@AllArgsConstructor
public class OAuthPathMapper {

    private OAuthPathRepository oAuthPathRepository;

    public String match(String requestPath) {
        AntPathMatcher antPathMatcher = new AntPathMatcher();

        String result = "empty";

        for(String path : oAuthPathRepository.getOAuthPaths()) {
            if (antPathMatcher.match(path, requestPath)) {
                result = path.split("/")[1];
                break;
            }
        }
        return result;
    }

    public ParamForCallBack getParam(Map<String, String[]> paramMap) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);

        return objectMapper.convertValue(convertMap(paramMap), ParamForCallBack.class);
    }

    private Map<String, String> convertMap(Map<String, String[]> paramMap) {
        HashMap<String, String> map = new HashMap<>();

        for(String key : paramMap.keySet()) {
            StringBuilder sb = new StringBuilder();

            int end = paramMap.get(key).length-1;
            for(String value : paramMap.get(key)) {
                sb.append(value);
                if(end > 0) {
                    sb.append(",");
                }
                end--;
            }
            map.put(key, sb.toString());
        }
        return map;
    }

}
