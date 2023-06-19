package com.spade.oauth.filter;

import com.spade.oauth.util.ParamMapUtil;
import com.spade.oauth.context.OAuthPathContext;
import com.spade.oauth.dto.OAuthResultToken;
import com.spade.oauth.dto.param.ParamForCallBack;
import com.spade.oauth.service.OAuthTokenServiceFactory;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * 로그인 요청 후 CallBack 요청을 처리하기 위한 Filter.
 * CallBack 요청 여부 확인 - 요청 타입 확인 - 요청 파라미터 추출 - OAuth 요청 결과 확인 - Event 발생.
 */

@RequiredArgsConstructor
public class OAuthServiceFilter extends OncePerRequestFilter {

    /** Request token을 처리하는 서비스 */
    private final OAuthTokenServiceFactory oAuthTokenServiceFactory;
    /** call back url등 path를 관리하는 Context */
    private final OAuthPathContext OAuthPathContext;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String type = OAuthPathContext.getType(request.getRequestURI());

        System.out.println("filter "+request.getRequestURL());

        /** CallBack 요청이 맞을 경우 */
        System.out.println("[TYPE]: "+type+" call");
        /** CallBack의 파라미터 추출(State, Code 등) */
        ParamForCallBack param = ParamMapUtil.getParam(request.getParameterMap());
        /** OAuth 요청 결과 */
        String result = oAuthTokenServiceFactory.requestToken(type, param);
        System.out.println("result in filter ? "+result);

        /** Publish Event */
        OAuthResultToken resultToken = oAuthTokenServiceFactory.send(result, request.getRequestURL().toString(), request.getQueryString());

        filterChain.doFilter(request, response);
    }
}
