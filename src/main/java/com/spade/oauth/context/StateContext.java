package com.spade.oauth.context;

import com.spade.oauth.domain.redis.OauthState;
import com.spade.oauth.redis.repository.OauthStateRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.SecureRandom;

@Service
@RequiredArgsConstructor
public class StateContext {

    private boolean STATE_USE_YN = true;

    public void setStateUseYn(boolean useYn) {
        this.STATE_USE_YN = useYn;
    }

    public boolean checkUsingState() {
        return this.STATE_USE_YN;
    }
}
