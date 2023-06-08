package com.spade.oauth.context;

import java.util.List;

public class OAuthContext {

    private String serviceBeanAttachedName = "Service";

    private String redisBeanAttachedName = "RedisService";

    public void setServiceBeanAttachedName(String name) {
        this.serviceBeanAttachedName = name;
    }

    public void setRedisBeanAttachedName(String name) {
        this.redisBeanAttachedName = name;
    }

}
