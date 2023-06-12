package com.spade.oauth.filter;

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

//
//@AllArgsConstructor
@RequiredArgsConstructor
@Component
public class OAuthServiceFilter extends OncePerRequestFilter {

    private final OAuthPathMapper oAuthPathMapper;

    private final OAuthTokenService oAuthTokenService;

    private final OAuthResultService oAuthResultService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestUri = request.getRequestURI();
        String matchResult = oAuthPathMapper.match(requestUri);

        if (!matchResult.equals("empty")) {
            OAuthType type = OAuthType.valueOf(matchResult.toUpperCase());
            System.out.println("["+type.type+"] request. "+requestUri);

            ParamForCallBack param = oAuthPathMapper.getParam(request.getParameterMap());
            String result = oAuthTokenService.requestToken(type, param);

            OAuthResultToken resultToken = oAuthResultService.send(result, request.getRequestURL().toString(), request.getQueryString());
//            response.setHeader("result", resultToken.toString());
        }

        filterChain.doFilter(request, response);
    }
}
