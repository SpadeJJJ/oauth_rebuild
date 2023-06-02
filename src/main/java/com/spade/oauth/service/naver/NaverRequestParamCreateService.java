package com.spade.oauth.service.naver;

import com.spade.oauth.dto.model.param.ParamAccessToken;
import com.spade.oauth.dto.model.param.StateInfoForParam;
import com.spade.oauth.service.RequestParamCreateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class NaverRequestParamCreateService implements RequestParamCreateService {

    @Value("${oauth2.naver.clientid}")
    private String clientId;
    @Value("${oauth2.naver.secretkey}")
    private String clientSecret;

    @Override
    public ParamAccessToken createParamForAccessTokenCreate(StateInfoForParam token) {
        ParamAccessToken param = new ParamAccessToken();

        setCommonParam(param);
        param.setGrantType("authorization_code");
        param.setState(token.getState());
        param.setCode(token.getCode());

        return param;
    }

    @Override
    public ParamAccessToken createParamForAccessTokenUpdate(StateInfoForParam token) {
        ParamAccessToken param = new ParamAccessToken();

        setCommonParam(param);
        param.setGrantType("refresh_token");
        param.setRefreshToken(token.getRefreshToken());

        return param;
    }

    @Override
    public ParamAccessToken createParamForAccessTokenDelete(StateInfoForParam token) {
        ParamAccessToken param = new ParamAccessToken();

        setCommonParam(param);
        param.setGrantType("delete");
        param.setAccessToken(token.getAccessToken());

        return param;
    }

    private void setCommonParam(ParamAccessToken param) {
        param.setClientId(clientId);
        param.setClientSecret(clientSecret);
        /*
        {"access_token":"AAAAOHJHAwJft4BIaHq1pNWxAX-cbd4d0BkKtHrCRoLfHGxQjkxYHajEYD3XWtGvF1qX0kPe98AF60urq8LtXJOq9Os",
        "refresh_token":"NcnQIqWJCBzo2hwXB0hE8U6T8C1J4KF6ysSTbfktP8kipY9X1gFTipsDTsq6MuSYW3fvv0AwAYZMispxFZgnqdhipj3HmaRjOniigX0gSPdjfpsWD1LSqWqjbzxqtiiG0NMLaU",
        "token_type":"bearer",
        "expires_in":"3600"}
         */
    }
}
