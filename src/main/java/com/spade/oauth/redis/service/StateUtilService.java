package com.spade.oauth.redis.service;

import com.spade.oauth.context.StateContext;
import com.spade.oauth.domain.redis.OAuthState;
import com.spade.oauth.redis.config.RedisConfig;
import com.spade.oauth.redis.repository.OAuthStateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.SecureRandom;


/**
 * OAuth 로그인 요청 시에 전송하는 State 관련 유틸 서비스
 *
 * createStateForOAuth : State 생성 후에 save()를 호출하여 redis에 저장.
 *
 * todo.
 * State 미사용 - 저장 안 함. (완성)
 * State 사용 && Redis 사용 - Redis에 저장 (완성)
 * State 사용 && Redis 미사용 - 다른 저장소에 저장하는 로직 구현 (예정)
 */
@Service
@ConditionalOnProperty(prefix = "spring.data.redis.repositories", name = "enabled", havingValue = "true",
        matchIfMissing = true)
@RequiredArgsConstructor
public class StateUtilService {

    private final OAuthStateRepository oauthStateRepository;

    private final StateContext stateContext;

    public String createStateForOAuth(String type) {
        if (!stateContext.checkStateUse()) {
            return "state is not used";
        }

        SecureRandom random = new SecureRandom();
        String state = new BigInteger(130, random).toString();
        save(state, type);

        return state;
    }

    public void save(String createdState, String type) {
        OAuthState state = new OAuthState();
        state.setState(createdState);
        state.setType(type);

        oauthStateRepository.save(state);
    }
}
