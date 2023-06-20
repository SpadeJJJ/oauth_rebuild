package com.spade.oauth.util;

import com.spade.oauth.context.OAuthPathContext;
import com.spade.oauth.dto.param.ParamForAccessToken;

/**
 * param 생성용 유틸
 * 삭제 예정
 */
public class ParamCreateUtil {

    private static final String PROPERTIES_PROFIX = "oauth2.authorize-info.";
    private static final String CALL_BACL_URI = ".call-back-uri";
    private static final String GRANT_TYPE = ".grant-type";
    private static final String CLIENT_ID = ".client-id";
    private static final String SECRET_KEY = ".secret-key";
    private static final String URL = ".request-token-url";

    /** token 생성에 필요한 param 생성 */
    public static void createAccessTokenParam(String type, ParamForAccessToken param, OAuthPathContext oAuthPathContext) {

        param.setClientId(oAuthPathContext.findProperties(PROPERTIES_PROFIX+type+CLIENT_ID));
        param.setGrantType(oAuthPathContext.findProperties(PROPERTIES_PROFIX+type+GRANT_TYPE));

        if (type.equals("naver")) {
            param.setClientSecret(oAuthPathContext.findProperties(PROPERTIES_PROFIX+type+SECRET_KEY));
        }
    }

//    public static String getUrlForAccessToken(String type, OAuthPathContext oAuthPathContext) {
//        return oAuthPathContext.findProperties(PROPERTIES_PROFIX+type+URL);
//    }
}