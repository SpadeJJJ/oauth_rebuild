package com.spade.oauth.event;

import com.spade.oauth.dto.OAuthResultToken;

/**
 * OAuth 인증 요청 결과 Listener 공통 인터페이스
 * (라이브러리를 사용하는 프로젝트에서 해당 인터페이스를 구현해서 사용)
 */

public interface OAuthResultEventHandler {

    public void takeResult(OAuthResultToken token);
}
