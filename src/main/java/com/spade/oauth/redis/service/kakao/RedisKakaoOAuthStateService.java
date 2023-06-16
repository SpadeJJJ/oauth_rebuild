package com.spade.oauth.redis.service.kakao;

import com.spade.oauth.domain.redis.OAuthState;
import com.spade.oauth.redis.config.RedisConfig;
import com.spade.oauth.redis.repository.OAuthStateRepository;
import com.spade.oauth.redis.service.RedisOAuthStateService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Redis에서 Kakao의 State를 처리하기 위한 서비스.
 * todo
 * 이거랑 naver랑 통합 예정
 */
@Service("kakaoRedisService")
@ConditionalOnProperty(prefix = "spring.data.redis.repositories", name = "enabled", havingValue = "true",
        matchIfMissing = true)
//@ConditionalOnBean(RedisConfig.class)
//@AllArgsConstructor
@RequiredArgsConstructor
public class RedisKakaoOAuthStateService implements RedisOAuthStateService {

    private final OAuthStateRepository oauthStateRepository;


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
