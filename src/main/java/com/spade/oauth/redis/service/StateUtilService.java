package com.spade.oauth.redis.service;

import com.spade.oauth.context.StateContext;
import com.spade.oauth.domain.redis.OAuthState;
import com.spade.oauth.redis.repository.OAuthStateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.SecureRandom;

@Service
//@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "spring.data.redis.repositories", name = "enabled", havingValue = "true",
        matchIfMissing = true)
public class StateUtilService {

    private OAuthStateRepository oauthStateRepository;

    private final StateContext stateContext;

    @Autowired(required = false)
    public StateUtilService(OAuthStateRepository oauthStateRepository, StateContext stateContext) {
        this.oauthStateRepository = oauthStateRepository;
        this.stateContext = stateContext;
    }

    public StateUtilService(StateContext stateContext) {
        this.stateContext = stateContext;
    }

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
