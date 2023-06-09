package com.spade.oauth.redis.service;

import com.spade.oauth.context.StateContext;
import com.spade.oauth.domain.redis.OauthState;
import com.spade.oauth.redis.repository.OauthStateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.SecureRandom;

@Service
@RequiredArgsConstructor
public class StateUtilService {

    private final OauthStateRepository oauthStateRepository;

    private final StateContext stateContext;

    public String createStateForOAuth(String type) {
        if (!stateContext.checkUsingState()) {
            return "state is not used";
        }

        SecureRandom random = new SecureRandom();
        String state = new BigInteger(130, random).toString();
        save(state, type);

        return state;
    }

    public void save(String createdState, String type) {
        OauthState state = new OauthState();
        state.setState(createdState);
        state.setType(type);

        oauthStateRepository.save(state);
    }
}
