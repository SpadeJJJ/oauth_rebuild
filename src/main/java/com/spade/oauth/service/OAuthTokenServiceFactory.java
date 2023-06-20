package com.spade.oauth.service;

import com.spade.oauth.context.OAuthPathContext;
import com.spade.oauth.dto.OAuthResultToken;
import com.spade.oauth.dto.param.ParamForAccessToken;
import com.spade.oauth.dto.param.ParamForCallBack;
import com.spade.oauth.exception.AuthorizeFailureException;
import com.spade.oauth.util.ParamCreateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

/**
 * token 요청을 처리하는 서비스를 관리.
 *
 * 삭제 예정
 */
@Service
@RequiredArgsConstructor
@DependsOn(value = {"OAuthPathContext"})
public class OAuthTokenServiceFactory {
    private final ApplicationContext applicationContext;

    /** token 결과 이벤트 생성용 publisher */
    private final ApplicationEventPublisher applicationEventPublisher;

    /** OAuthPath 관리 context */
    private final OAuthPathContext oAuthPathContext;

    /** 토큰 생성 요청 */
    public String requestToken(String type, ParamForCallBack param) {
        String serviceName = type + "Service";
        String result = "";

        try {
            /**
             * type에 따른 OAuthService Bean 찾기
             * ex. type = naver -> NaverOAuthService Bean.
             */
            OAuthService service = (OAuthService)applicationContext.getBean(serviceName);

            /** token 요청에 필요한 param 생성 */
            ParamForAccessToken accessTokenParam = new ParamForAccessToken();
            accessTokenParam.setCode(param.getCode());
            accessTokenParam.setState(param.getState());
            ParamCreateUtil.createAccessTokenParam(type, accessTokenParam, oAuthPathContext);

            /** 토큰 생성 요청 */
            result = service.requestAccessToken(accessTokenParam);
        } catch (AuthorizeFailureException e) {
            return e.getMessage();
        } catch (NoSuchBeanDefinitionException e) {
            return "not support type";
        } catch (Exception e) {
            return e.getMessage();
        }

        return  result;
    }

    public OAuthResultToken send(String result, String url, String param) {
        OAuthResultToken resultToken = new OAuthResultToken(result, url, param);
        applicationEventPublisher.publishEvent(resultToken);
        return resultToken;
    }
}
