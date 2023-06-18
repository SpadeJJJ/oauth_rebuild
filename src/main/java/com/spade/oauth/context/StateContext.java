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

    private static boolean STATE_USE = true;

    private static boolean REDIS_USE = false;

    private Map<String, String> stateMap = new HashMap<>();

    public void setStateUse(boolean use) {
        this.STATE_USE = use;

        if (!STATE_USE) {
            REDIS_USE = false;
        }
    }

    public void setRedisUse(boolean use) {
        this.REDIS_USE = use;
    }

    public boolean checkStateUse() {
        return this.STATE_USE;
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

    public static boolean isUseState() {
        return STATE_USE;
    }

    public static boolean isUseRedis() {
        return REDIS_USE;
    }
}
