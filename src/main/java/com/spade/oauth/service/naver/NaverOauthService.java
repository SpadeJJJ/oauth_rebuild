package com.spade.oauth.service.naver;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.spade.oauth.domain.redis.OauthState;
import com.spade.oauth.dto.model.param.ParamAccessToken;
import com.spade.oauth.dto.model.response.OauthResponse;
import com.spade.oauth.exception.ResultNullPointerException;
import com.spade.oauth.fegin.NaverOauthClient;
import com.spade.oauth.redis.RedisInfo;
import com.spade.oauth.redis.service.OauthStateService;
import com.spade.oauth.service.OauthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "naverOauthService")
public class NaverOauthService implements OauthService {

    @Autowired
    private NaverOauthClient naverOauthClient;

    @Autowired
    private OauthStateService oauthStateService;

    @Override
    public String requestAuthorizeTokenCreate(ParamAccessToken token) {
        String result = null;

        try {
            result = naverOauthClient.requestAccessTokenCreate(token);
//            Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
            OauthState state = oauthStateService.findState(token.getState()).get();
            if(state == null) {
                throw new NullPointerException("there isn`t state");
            }

            oauthStateService.deleteOauthState(state.getId());
            if(result == null) {
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
    public String requestAuthorizeTokenUpdate(ParamAccessToken token) {
        String result = null;
        try {
            result = naverOauthClient.requestAccessTokenUpdate(token);
            Gson gson = new Gson();

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
    public String requestAuthorizeTokenDelete(ParamAccessToken token) {
        String result = null;

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