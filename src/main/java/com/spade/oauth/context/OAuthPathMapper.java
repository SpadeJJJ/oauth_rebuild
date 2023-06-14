package com.spade.oauth.context;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.spade.oauth.dto.model.param.ParamForCallBack;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.AntPathRequestMatcherProvider;
import org.springframework.boot.autoconfigure.security.servlet.RequestMatcherProvider;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * CallBack url Mather.
 *
 * todo.
 * 변경 예정
 */
@Component
@RequiredArgsConstructor
public class OAuthPathMapper {
    private final OAuthPathContext oAuthPathContext;

    public String match(String requestUrl) {
        String result = null;
//
        for (String key : oAuthPathContext.getAuthorizeInfo().keySet()) {
            Pattern pattern = Pattern.compile(oAuthPathContext.getAuthorizeInfo().get(key).getCallBackUrl() +"*");
            Matcher matcher = pattern.matcher(requestUrl);
            if(matcher.find()) {
                result = key;
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
