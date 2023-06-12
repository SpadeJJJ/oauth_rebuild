package com.spade.oauth.redis.service;

import com.spade.oauth.domain.redis.OAuthState;

import java.util.Optional;

public interface RedisOAuthStateService {
    public boolean checkState(String state);

    public Optional<OAuthState> findState(String state);

    public void deleteOauthState(Long id);
}
