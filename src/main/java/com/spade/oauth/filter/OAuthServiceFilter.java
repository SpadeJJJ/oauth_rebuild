package com.spade.oauth.filter;

import com.spade.oauth.chain.Chain;
import com.spade.oauth.manager.OAuthChainManager;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Map;

/**
 * 로그인 요청 후 CallBack 요청을 처리하기 위한 Filter.
 * CallBack 요청 여부 확인 - 요청 타입 확인 - 요청 파라미터 추출 - OAuth 요청 결과 확인 - Event 발생.
 */

@RequiredArgsConstructor
public class OAuthServiceFilter extends OncePerRequestFilter {

    /** Request token을 처리하는 서비스 */
//    private final OAuthTokenServiceFactory oAuthTokenServiceFactory;
    /** call back url등 path를 관리하는 Context */
//    private final OAuthPropertyManager oAuthPropertyManager;

    private final OAuthChainManager oAuthChainManager;

    private final Map<String, String> urls;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String type = urls.get(request.getRequestURI());

        System.out.println("[TYPE]: "+type+" call");
        /** CallBack의 파라미터 추출(State, Code 등) */
//        ParamForCallBack param = ParamMapUtil.getParam(request.getParameterMap());
        /** OAuth 요청 결과 */
//        String result = oAuthTokenServiceFactory.requestToken(type, param);
        Chain oAuthChain = oAuthChainManager.getChain(type);
        oAuthChain.request(type, request.getParameterMap());
//        System.out.println("result in filter ? "+result);

        /** Publish Event */
//        OAuthResultToken resultToken = oAuthTokenServiceFactory.send(result, request.getRequestURL().toString(), request.getQueryString());

        filterChain.doFilter(request, response);
    }
}
