package com.spade.oauth.redis.service.naver;

import com.spade.oauth.domain.redis.OAuthState;
import com.spade.oauth.redis.repository.OAuthStateRepository;
import com.spade.oauth.redis.service.RedisOAuthStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("naverRedisService")

@ConditionalOnProperty(prefix = "spring.data.redis.repositories", name = "enabled", havingValue = "true",
        matchIfMissing = true)
//@RequiredArgsConstructor
public class RedisNaverOAuthStateService implements RedisOAuthStateService {
    private final OAuthStateRepository oauthStateRepository;

    @Autowired(required = false)
    public RedisNaverOAuthStateService(OAuthStateRepository oauthStateRepository) {
        this.oauthStateRepository = oauthStateRepository;
    }

    public boolean checkState(String state) {
        Optional<OAuthState> result = oauthStateRepository.findByState(state);
        return result.isPresent() ? true : false;
    }

    public Optional<OAuthState> findState(String state) {
        Optional<OAuthState> result = oauthStateRepository.findByState(state);
        return result;
    }

    public void deleteOauthState(Long id) {
        oauthStateRepository.deleteById(id);
    }
}
