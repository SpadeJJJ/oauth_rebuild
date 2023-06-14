package com.spade.oauth.filter;

import com.spade.oauth.context.OAuthPathContext;
import com.spade.oauth.context.OAuthPathMapper;
import com.spade.oauth.context.OAuthType;
import com.spade.oauth.dto.model.OAuthResultToken;
import com.spade.oauth.event.OAuthResultService;
import com.spade.oauth.dto.model.param.ParamForCallBack;
import com.spade.oauth.service.OAuthTokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;

/**
 * OAuth 로그인 요청 후 CallBack 요청을 처리하기 위한 Filter.
 *
 * CallBack 요청 여부 확인 - 요청 타입 확인 - 요청 파라미터 추출 - OAuth 요청 결과 확인 - Event 발생.
 */
@RequiredArgsConstructor
public class OAuthServiceFilter extends OncePerRequestFilter {

    private final OAuthPathMapper oAuthPathMapper;

    private final OAuthTokenService oAuthTokenService;

    private final OAuthResultService oAuthResultService;

//    private final OAuthPathContext oAuthPathContext;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {




        /** CallBack 요청이 맞을 경우 */
        if (matchResult != null) {
            /** 요청 타입.(naver, kakao 등) */
//            OAuthType type = OAuthType.valueOf(matchResult.toUpperCase());

            OAuthType type = OAuthType.valueOf("nave");
            System.out.println("["+type.getName()+"] request. "+requestUrl);

            /** CallBack 요청의 파라미터 추출(State, Code 등) */
            ParamForCallBack param = oAuthPathMapper.getParam(request.getParameterMap());
            /** OAuth 요청 결과 */
            String result = oAuthTokenService.requestToken(type, param);

            /** Publish Event */
            OAuthResultToken resultToken = oAuthResultService.send(result, request.getRequestURL().toString(), request.getQueryString());
        }

        filterChain.doFilter(request, response);
    }
}
