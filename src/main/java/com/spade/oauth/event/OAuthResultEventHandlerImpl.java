package com.spade.oauth.event;

import com.spade.oauth.dto.OAuthResultToken;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * Request token 결과 Listener 구현 인터페이스
 */

@Component
public class OAuthResultEventHandlerImpl implements OAuthResultEventHandler {

    /**
     * Request token 결과 subscribe 클래스.
     */
    @Override
    @EventListener
    public void takeResult(OAuthResultToken token) {
        System.out.println("=====================================================");
        System.out.println("Event Listener sub");
        System.out.println(token.getResult());
        System.out.println(token.getType());
        System.out.println(token.getParam());
        System.out.println("=====================================================");
    }
}
