package com.spade.oauth.manager;

import com.spade.oauth.OAuthCorporationType;
import com.spade.oauth.property.OAuthProperty;
import org.springframework.core.env.Environment;

import java.util.HashMap;
import java.util.Map;

import static com.spade.oauth.PropertyVariable.*;

public class OAuthPropertyManager {

    private Map<String, OAuthProperty> oAuthPropertyMap = new HashMap<>();

    private final Environment environment;

    public OAuthPropertyManager(Environment environment) {
        this.environment = environment;
    }

    public OAuthProperty getProperty(String type) {
        if (oAuthPropertyMap.containsKey(type)) {
            return oAuthPropertyMap.get(type);
        }

        OAuthProperty oAuthProperty = createOAuthProperty(type);
        oAuthPropertyMap.put(type, oAuthProperty);

        return oAuthProperty;
    }

    private OAuthProperty createOAuthProperty(String type) {
        OAuthProperty property = new OAuthProperty();
        if (OAuthCorporationType.isCommonType(type)) {
            property.setRequestTokenUrl(environment.getProperty(PROPERTIES_PREFIX+type+REQUEST_TOKEN_URL));
            property.setClientId(environment.getProperty(PROPERTIES_PREFIX+type+CLIENT_ID));
            property.setCallBackUri(environment.getProperty(PROPERTIES_PREFIX+type+CALL_BACK_URI));
            property.setCallBackHost(environment.getProperty(PROPERTIES_PREFIX+type+CALL_BACK_HOST));
            property.setGrantType(environment.getProperty(PROPERTIES_PREFIX+type+GRANT_TYPE));
            property.setAuthorizeUrl(environment.getProperty(PROPERTIES_PREFIX+type+AUTHORIZE_URL));
            property.setSecretKey(environment.getProperty(PROPERTIES_PREFIX+type+SECRET_KEY));
        }

        return property;
    }

    public Map<String, String> getCallbackUriList() {
        Map<String, String> types = new HashMap<>();

        for (String type : OAuthCorporationType.getTypes()) {
            types.put(environment.getProperty(PROPERTIES_PREFIX+type+CALL_BACK_URI), type);
        }

        return types;
    }

    public String getRequestTokenUrl(String type) {
        System.out.println(PROPERTIES_PREFIX+type+REQUEST_TOKEN_URL);
        return oAuthPropertyMap.get(type).getRequestTokenUrl();
//        return environment.getProperty(PROPERTIES_PREFIX+type+REQUEST_TOKEN_URL);
    }

    public String getType(String requestUri) {
        String result = null;
        for (String type : oAuthPropertyMap.keySet()) {
            if (oAuthPropertyMap.get(type).getCallBackUri().equals(requestUri)) {
                result = type;
                break;
            }
        }
        return result;
    }

}
