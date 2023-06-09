package com.spade.oauth.event;

import com.spade.oauth.dto.model.OAuthResultToken;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@AllArgsConstructor
public class OAuthResultService {

    private final ApplicationEventPublisher applicationEventPublisher;

    public OAuthResultToken send(String result, String url, String param) {
        System.out.println("will send "+result);
        OAuthResultToken resultToken = new OAuthResultToken(result, url, param);
        applicationEventPublisher.publishEvent(resultToken);
        return resultToken;
    }

}
