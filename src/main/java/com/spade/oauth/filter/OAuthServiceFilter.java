package com.spade.oauth.filter;

import com.spade.oauth.context.ParamMapUtil;
import com.spade.oauth.context.OAuthPathContext;
import com.spade.oauth.dto.model.OAuthResultToken;
import com.spade.oauth.dto.model.param.ParamForCallBack;
import com.spade.oauth.service.OAuthTokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * OAuth 로그인 요청 후 CallBack 요청을 처리하기 위한 Filter.
 * CallBack 요청 여부 확인 - 요청 타입 확인 - 요청 파라미터 추출 - OAuth 요청 결과 확인 - Event 발생.
 */
@RequiredArgsConstructor
public class OAuthServiceFilter extends OncePerRequestFilter {

    private final OAuthTokenService oAuthTokenService;

    private final OAuthPathContext OAuthPathContext;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestUri = request.getRequestURI();
        String type = OAuthPathContext.match(requestUri);

        /** CallBack 요청이 맞을 경우 */
        if (type != null) {
            System.out.println("[TYPE]: "+type+" call");
            /** CallBack 요청의 파라미터 추출(State, Code 등) */
            ParamForCallBack param = ParamMapUtil.getParam(request.getParameterMap());
            /** OAuth 요청 결과 */
            String result = oAuthTokenService.requestToken(type, param);

            /** Publish Event */
            OAuthResultToken resultToken = oAuthTokenService.send(result, request.getRequestURL().toString(), request.getQueryString());
        }

        filterChain.doFilter(request, response);
    }
}
