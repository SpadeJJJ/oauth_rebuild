package com.spade.oauth.context;

import com.spade.oauth.service.OauthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class BeanTest {

    @Autowired
    public Map<String, OauthService> imps;

    public void pi(){
        System.out.println(this.imps.keySet());
    }
}
