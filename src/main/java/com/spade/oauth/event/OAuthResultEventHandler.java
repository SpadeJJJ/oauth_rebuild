package com.spade.oauth.event;


import com.spade.oauth.dto.model.OAuthResultToken;

public interface OAuthResultEventHandler {

    public void takeResult(OAuthResultToken token);
}
