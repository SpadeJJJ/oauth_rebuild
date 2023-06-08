package com.spade.oauth.redis.service.kakao;

import com.spade.oauth.domain.redis.OauthState;
import com.spade.oauth.redis.repository.OauthStateRepository;
import com.spade.oauth.redis.service.RedisOauthStateService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("kakaoRedisService")
@AllArgsConstructor
public class RedisKakaoOauthStateService implements RedisOauthStateService {

    private OauthStateRepository oauthStateRepository;

//    public RedisKakaoOauthStateService(OauthStateRepository oauthStateRepository) {
//        this.oauthStateRepository = oauthStateRepository;
//    }

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
