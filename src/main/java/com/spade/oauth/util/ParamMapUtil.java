package com.spade.oauth.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.spade.oauth.dto.param.ParamForCallBack;

import java.util.HashMap;
import java.util.Map;


/**
 * CallBack의 param(State, Code 등) 매핑 유틸
 */
public class ParamMapUtil {

    /** callback의 param -> ParamForCallBack(token 요청용 param) 매핑 */
    public static ParamForCallBack getParam(Map<String, String[]> paramMap) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);

        return objectMapper.convertValue(convertMap(paramMap), ParamForCallBack.class);
    }

    /** paramMap 형변환
     * <String, String[]> -> <String, String>
     */
    private static Map<String, String> convertMap(Map<String, String[]> paramMap) {
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
