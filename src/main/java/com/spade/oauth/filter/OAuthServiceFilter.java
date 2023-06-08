package com.spade.oauth.filter;

import com.spade.oauth.context.OAuthPathMapper;
import com.spade.oauth.context.OAuthType;
import com.spade.oauth.event.OAuthResultService;
import com.spade.oauth.dto.model.param.ParamForCallBack;
import com.spade.oauth.service.OAuthTokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

//
@AllArgsConstructor
@Component
public class OAuthServiceFilter extends OncePerRequestFilter {

    private final OAuthPathMapper oAuthPathMapper;

    private final OAuthTokenService oAuthTokenService;

    private final OAuthResultService oAuthResultService;

//    private final OAuthResultService eventService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestUri = request.getRequestURI();
        String matchResult = oAuthPathMapper.match(requestUri);

        System.out.println("filter "+requestUri);

        if (!matchResult.equals("empty")) {
            System.out.println("match! ");
            oAuthResultService.send("1", "2", "3");
            OAuthType type = OAuthType.valueOf(matchResult.toUpperCase());
            System.out.println("type: "+type.type);
            ParamForCallBack param = oAuthPathMapper.getParam(request.getParameterMap());
            String result = oAuthTokenService.requestToken(type, param);
            System.out.println("get result "+result);
            oAuthResultService.send(result, request.getRequestURL().toString(), request.getQueryString());

        }

        filterChain.doFilter(request, response);
    }
}
