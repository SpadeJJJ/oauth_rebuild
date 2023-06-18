package com.spade.oauth.util;

import com.spade.oauth.context.OAuthPathContext;
import com.spade.oauth.dto.model.param.ParamForAccessToken;

public class ParamCreateUtil {

    private static final String PROPERTIES_PROFIX = "oauth2.authorize-info.";
    private static final String CALL_BACL_URL = ".call-back-url";
    private static final String GRANT_TYPE = ".grant-type";
    private static final String CLIENT_ID = ".client-id";
    private static final String SECRET_KEY = ".secret-key";
    private static final String URL = ".host-url";

    public static void createAccessTokenParam(String type, ParamForAccessToken param, OAuthPathContext oAuthPathContext) {

        if (type.equals("naver")) {
            param.setClientId(oAuthPathContext.findProperties(PROPERTIES_PROFIX+type+CLIENT_ID));
            param.setGrantType(oAuthPathContext.findProperties(PROPERTIES_PROFIX+type+GRANT_TYPE));
            param.setClientSecret(oAuthPathContext.findProperties(PROPERTIES_PROFIX+type+SECRET_KEY));
        } else if(type.equals("kakao")) {
            param.setClientId(oAuthPathContext.findProperties(PROPERTIES_PROFIX+type+CLIENT_ID));
            param.setGrantType(oAuthPathContext.findProperties(PROPERTIES_PROFIX+type+GRANT_TYPE));
        }
    }

    public static String getUrlForAccessToken(String type, OAuthPathContext oAuthPathContext) {
        return oAuthPathContext.findProperties(PROPERTIES_PROFIX+type+URL);
    }
}