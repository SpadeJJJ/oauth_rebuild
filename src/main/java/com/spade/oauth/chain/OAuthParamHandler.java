package com.spade.oauth.chain;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.spade.oauth.dto.param.ParamForAccessToken;
import com.spade.oauth.dto.param.ParamForCallBack;
import com.spade.oauth.manager.OAuthPropertyManager;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;

import java.util.HashMap;
import java.util.Map;

import static com.spade.oauth.PropertyVariable.*;

@RequiredArgsConstructor
public class OAuthParamHandler extends Chain {

//    private final Environment environment;

    private final OAuthPropertyManager oAuthPropertyManager;
    @Override
    public void request(String type, Map<String, String[]> map) {
        Map<String, String> convertedMap = convertMap(map);
        ParamForAccessToken param = getParamFromCallBack(convertedMap);
        setParamValueForAccessToken(type, param);
        System.out.println("param chain ");
        if (checkNext()) {
            next.request(type, convertedMap, param);
        }
    }

    public ParamForAccessToken getParamFromCallBack(Map<String, String> convertedMap) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        ParamForCallBack paramForCallBack = objectMapper.convertValue(convertedMap, ParamForCallBack.class);

        /** todo exception add */

        ParamForAccessToken result = new ParamForAccessToken();
        result.setCode(paramForCallBack.getCode());
        result.setState(paramForCallBack.getState());

        return result;
    }

    private Map<String, String> convertMap(Map<String, String[]> map) {
        HashMap<String, String> result = new HashMap<>();

        for(String key : map.keySet()) {
            StringBuilder sb = new StringBuilder();

            int end = map.get(key).length-1;
            for(String value : map.get(key)) {
                sb.append(value);
                if(end > 0) {
                    sb.append(",");
                }
                end--;
            }
            result.put(key, sb.toString());
        }
        return result;
    }

    public void setParamValueForAccessToken(String type, ParamForAccessToken param) {

        param.setClientId(oAuthPropertyManager.getProperty(type).getClientId());
        param.setGrantType(oAuthPropertyManager.getProperty(type).getGrantType());
        param.setClientSecret(oAuthPropertyManager.getProperty(type).getSecretKey());
//        param.setClientId(environment.getProperty(PROPERTIES_PREFIX +type+CLIENT_ID));
//        param.setGrantType(environment.getProperty(PROPERTIES_PREFIX +type+GRANT_TYPE));
//        param.setClientSecret(environment.getProperty(PROPERTIES_PREFIX +type+SECRET_KEY));
    }

    @Override
    public void request(String type, Map<String, String> map, ParamForAccessToken param) {}

    @Override
    public void request(String type, String result, Map<String, String> map) {}
}
