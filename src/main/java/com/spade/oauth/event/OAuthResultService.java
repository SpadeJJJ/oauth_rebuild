package com.spade.oauth.event;

import com.spade.oauth.dto.model.OAuthResultToken;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * OAuth 인증 요청 결과 처리 서비스
 * (Event Publisher)
 */
@Service
@RequiredArgsConstructor
public class OAuthResultService {

    private final ApplicationEventPublisher applicationEventPublisher;

    public OAuthResultToken send(String result, String url, String param) {
        System.out.println("will send "+result);
        OAuthResultToken resultToken = new OAuthResultToken(result, url, param);
        applicationEventPublisher.publishEvent(resultToken);
        return resultToken;
    }

}
