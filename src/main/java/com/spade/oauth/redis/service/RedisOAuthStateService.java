package com.spade.oauth.redis.service;

import com.spade.oauth.domain.redis.OAuthState;

import java.util.Optional;

/**
 * Redis에서 State를 처리하기 위한 공통 인터페이스.
 */

public interface RedisOAuthStateService {
    public boolean checkState(String state);

    public Optional<OAuthState> findState(String state);

    public void deleteOauthState(Long id);
}
