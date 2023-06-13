package com.spade.oauth.context;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * State 관리용 Context.
 *
 * 1. State 사용 여부 관리
 * 2. State 사용 및 Redis 미사용시 Map으로 State 관리.
 */
@Component
@RequiredArgsConstructor
public class StateContext {

    private boolean STATE_USE_YN = true;

    private Map<String, String> stateMap = new HashMap<>();

    public void setStateUse(boolean useYn) {
        this.STATE_USE_YN = useYn;
    }

    public boolean checkStateUse() {
        return this.STATE_USE_YN;
    }

    public boolean checkState(String state) {
        return stateMap.containsKey(state) ? true : false;
    }

    public void deleteState(String state) {
        stateMap.remove(state);
    }

    public void setState(String state, String type) {
        stateMap.put(state, type);
    }
}
