package com.spade.oauth.context;

import com.spade.oauth.service.OauthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class OAuthContextService implements InitializingBean {
    private final ApplicationContext context;
    private final OAuthPathRepository oAuthPathRepository;

    private final OAuthPathMapper oAuthPathMapper;


    //1. 패키지 + authName한 곳의 OAuthService 클래스를 구현한 클래스를 찾는다.
    //2. 1번의 클래스 기준으로 bean을 getBean하여 컴포넌트 자체를 반환하다.

    public OauthService findService(String authName){
//        String getG = oAuthPathMapper.match("/naver/callback");
//        System.out.println("mapper "+getG);
//        String a = OAuthType.KAKAO.type;
//        OauthService service = (OauthService) context.getBean("kakao");
//        System.out.println(service.getClass().toString());

        return null;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
//        findService("d");
//        String get = oAuthPathRepository.test();
//        get = get.toUpperCase();
//        OAuthType a = OAuthType.valueOf(get);
//        System.out.println("this is type  "+a.type);

        // 팀장님 1
//        OauthService oauthService = context.getBean();
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
