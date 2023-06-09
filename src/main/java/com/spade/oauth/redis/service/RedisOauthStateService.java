package com.spade.oauth.redis.service;

import com.spade.oauth.domain.redis.OauthState;

import java.util.Optional;

public interface RedisOauthStateService {
    public boolean checkState(String state);

    public Optional<OauthState> findState(String state);

    public void deleteOauthState(Long id);
}
