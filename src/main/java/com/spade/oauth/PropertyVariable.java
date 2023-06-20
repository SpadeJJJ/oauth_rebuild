package com.spade.oauth;

import java.lang.annotation.Native;

public final class PropertyVariable {

    @Native
    public static final String PROPERTIES_PREFIX = "oauth2.authorize-info.";
    @Native
    public static final String GRANT_TYPE = ".grant-type";
    @Native
    public static final String CLIENT_ID = ".client-id";
    @Native
    public static final String SECRET_KEY = ".secret-key";
    @Native
    public static final String REQUEST_TOKEN_URL = ".request-token-url";
    @Native
    public static final String CALL_BACK_URI = ".call-back-uri";
    @Native
    public static final String CALL_BACK_HOST = ".call-back-host";
    @Native
    public static final String AUTHORIZE_URL = ".authorize-url";
}
