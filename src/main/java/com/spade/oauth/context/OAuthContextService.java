package com.spade.oauth.context;

import com.google.gson.Gson;
import com.spade.oauth.controller.TestController;
import com.spade.oauth.dto.model.param.ParamForStateInfo;
import com.spade.oauth.service.OauthService;
import com.spade.oauth.service.kakao.KakaoOauthService;
import com.spade.oauth.service.naver.NaverOauthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RequiredArgsConstructor
@Component
public class OAuthContextService implements InitializingBean {
    private final ApplicationContext context;

    public OauthService findService(String authName){
        //1. 패키지 + authName한 곳의 OAuthService 클래스를 구현한 클래스를 찾는다.
        //2. 1번의 클래스 기준으로 bean을 getBean하여 컴포넌트 자체를 반환하다.
    }

    @Override
    public void afterPropertiesSet() throws Exception {

        OauthService oauthService = context.getBean();
    }

//    @Override
//    public void afterPropertiesSet() throws Exception {
//        List<Controller> controllerList = context.getBean();
//        Controller c;
//        controllerList.for() {
//            Field f = c.getField();
//            c.after(
//                    doIt(f);
//            );
//        }
//    }
//
//    private void doIt(AuthType authType){
//        String result = "AuthorizeRequest fail";
//
//        if(redisKakaoOauthStateService.checkState(response.getState())) {
//            logger.info("[kakao] state check pass: "+response.getState());
//            ParamForStateInfo param = ParamForStateInfo.toAutorize(code);
//            ParamForStateInfo.builder()
//                             .code(response.getCode())
//                             .state(response.getState())
//                             .build()
//            kakaoRequestParamCreateService.createParamForAccessTokenCreate()
//            result = OAuthService.requestForAuthorizeTokenCreate();
//        } else {
//            result += " "+response.getErrorDescription();
//        }
//
//        return result;
//    }
}
