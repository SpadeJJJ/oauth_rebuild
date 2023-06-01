package com.spade.oauth.service.naver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.google.gson.Gson;
import com.spade.oauth.dto.model.ParamAccessToken;
import com.spade.oauth.exception.ResultNullPointerException;
import com.spade.oauth.fegin.NaverOauthClient;
import com.spade.oauth.fegin.Test;
import com.spade.oauth.service.OauthService;
import feign.Contract;
import feign.Feign;
import feign.auth.BasicAuthRequestInterceptor;
import feign.codec.Decoder;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Map;

@Service(value = "naverOauthService")
public class NaverOauthService implements OauthService {

    @Autowired
    private NaverOauthClient naverOauthClient;

    @Value("${oauth2.naver.login.authorize.host.url}")
    private String naverOauthHostUrl;

    @Value("${oauth2.naver.login.authorize.token.url}")
    private String naverOauthServiceUrl;

//    @Override
//    public String requestAuthorize(Map<String, Object> requestParams) {
//        String result="";
//        try {
//            String responseType = (String)requestParams.get("responseType");
//            String clientId = (String)requestParams.get("clientId");
//            String redirectUri = (String)requestParams.get("redirectUri");
//            String state = (String)requestParams.get("state");
//
////            naverOauthClient.requestAuthorize(responseType, clientId, redirectUri, state);
////            result = test.requestAuthorize(responseType, clientId, redirectUri, state);
//            result = "https://nid.naver.com/oauth2.0/authorize?response_type="+responseType+"&client_id="+clientId+"&redirect_uri="+redirectUri+"&state="+state;
//        } catch (NullPointerException e) {
//            System.out.println(e.getMessage());
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//        return result;
//    }

    @Override
    public Gson requestAuthorizeTokenUpdate(ParamAccessToken token) {
        Gson result = null;
        try {
//            result = naverOauthClient.requestAccessTokenUpdate(token.getGrantType(), token.getClientId(), token.getClientSecret(), token.getRefreshToken());

            if(result == null) {
                throw new ResultNullPointerException("naver access token update fail");
            }

        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public Gson requestAuthorizeTokenCreate(ParamAccessToken token) {
        Gson result = null;

        try {
//            result = naverOauthClient.requestAccessTokenService(token.getClientId(), token.getClientSecret(), token.getGrantType(), token.getState(), token.getCode());
//            NaverOauthClient naverOauthClient = Feign.builder()
//                                                     .encoder(new GsonEncoder())
////                                                     .decoder(new GsonDecoder())
////                                                     .client(new OkHttpClient())
////                                                     .requestInterceptor(new BasicAuthRequestInterceptor("response_type", token.getResponseType()))
////                                                     .requestInterceptor(new BasicAuthRequestInterceptor("client_id", token.getClientId()))
////                                                     .requestInterceptor(new BasicAuthRequestInterceptor("redirect_uri", token.getRedirect_uri()))
////                                                     .requestInterceptor(new BasicAuthRequestInterceptor("state", token.getState()))
//                                                     .contract(new Contract.Default())
//                                                     .target(NaverOauthClient.class, naverOauthHostUrl);



            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);


            ParamAccessToken temp = objectMapper.readValue(objectMapper.writeValueAsString(token), ParamAccessToken.class);
            Map<String, String> map = objectMapper.readValue(objectMapper.writeValueAsString(token), Map.class);
            System.out.println(map.keySet());
//            System.out.println(objectMapper.writeValueAsString(token));
//            String result1 = naverOauthClient.requestAccessTokenCreate(token.getClientId(), token.getClientSecret(), token.getGrantType(), token.getState(), token.getCode());
            String result1 = naverOauthClient.requestAccessTokenCreate(temp);
            System.out.println("result "+result1);
            if(result1 == null) {
                throw new ResultNullPointerException("naver access token create fail");
            }

        } catch (NullPointerException e) {
            System.out.println("null " +e.getMessage());
        } catch (Exception e) {
            System.out.println("ex "+e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Gson requestAuthorizeTokenDelete(ParamAccessToken token) {
        Gson result = null;

        try {
//            result = naverOauthClient.requestAccessTokenDelete(token.getGrantType(), token.getClientId(), token.getClientSecret(), token.getAccessToken(), token.getServiceProvider());

            if(result == null) {
                throw new ResultNullPointerException("naver access token delete fail");
            }

        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }
}