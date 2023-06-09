package com.spade.oauth.redis.service.naver;

import com.spade.oauth.domain.redis.OauthState;
import com.spade.oauth.redis.repository.OauthStateRepository;
import com.spade.oauth.redis.service.RedisOauthStateService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("naverRedisService")
@RequiredArgsConstructor
public class RedisNaverOauthStateService implements RedisOauthStateService {
    private final OauthStateRepository oauthStateRepository;

    public boolean checkState(String state) {
        Optional<OauthState> result = oauthStateRepository.findByState(state);
        return result.isPresent() ? true : false;
    }

    public Optional<OauthState> findState(String state) {
        Optional<OauthState> result = oauthStateRepository.findByState(state);
        return result;
    }

    public void deleteOauthState(Long id) {
        oauthStateRepository.deleteById(id);
    }
}
