package com.spade.oauth.event;

import com.spade.oauth.dto.model.OAuthResultToken;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class OAuthResultEventHandlerImpl implements OAuthResultEventHandler{
    @Override
    @EventListener
    public void takeResult(OAuthResultToken token) {
        System.out.println("=====================================================");
        System.out.println("Event Listener sub");
        System.out.println(token.getResult());
        System.out.println(token.getCallbackUrl());
        System.out.println(token.getParam());
        System.out.println("=====================================================");
    }
}
