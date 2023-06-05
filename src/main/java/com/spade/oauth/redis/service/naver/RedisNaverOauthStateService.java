package com.spade.oauth.redis.service.naver;

import com.spade.oauth.domain.redis.OauthState;
import com.spade.oauth.redis.repository.OauthStateRepository;
import com.spade.oauth.redis.service.RedisOauthStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RedisNaverOauthStateService implements RedisOauthStateService {

    @Value("${oauth2.naver.clientid}")
    private String clientId;
    @Value("${oauth2.naver.secretkey}")
    private String secretKey;

    @Autowired
    OauthStateRepository oauthStateRepository;

    public boolean checkState(String state) {
        Optional<OauthState> result = oauthStateRepository.findByState(state);
        return result.isPresent() ? true : false;
    }

    public Optional<OauthState> findState(String state) {
        Optional<OauthState> result = oauthStateRepository.findByState(state);
        return result;
    }
    public void saveOauthState(String state, String type) {
        OauthState oauthState = new OauthState();
        oauthState.setState(state);
        oauthState.setType(type);

        oauthStateRepository.save(oauthState);
    }

    public void deleteOauthState(Long id) {
        oauthStateRepository.deleteById(id);
    }
}
