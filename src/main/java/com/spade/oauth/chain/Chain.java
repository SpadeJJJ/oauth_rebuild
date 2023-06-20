package com.spade.oauth.chain;

import com.spade.oauth.dto.param.ParamForAccessToken;

import java.util.Map;

public abstract class Chain {
    protected Chain next;

    public Chain setChain(Chain chain) {
        this.next = chain;
        return next;
    }


    public boolean checkNext() {
        if (this.next == null) {
            return false;
        }
        return true;
    }

    public abstract void request(String type, Map<String, String[]> map);

    public abstract void request(String type, Map<String, String> map, ParamForAccessToken param);

    public abstract void request(String type, String result, Map<String, String> map);
}
