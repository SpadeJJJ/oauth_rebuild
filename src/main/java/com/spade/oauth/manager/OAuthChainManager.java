package com.spade.oauth.manager;

import com.spade.oauth.OAuthCorporationType;
import com.spade.oauth.chain.Chain;
import com.spade.oauth.chain.CommonOAuthServiceHandler;
import com.spade.oauth.chain.OAuthEventPublisherHandler;
import com.spade.oauth.chain.OAuthParamHandler;
import com.spade.oauth.manager.OAuthServiceManager;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.core.env.Environment;

public class OAuthChainManager {
    private final ApplicationEventPublisher applicationEventPublisher;

    private final OAuthServiceManager oAuthServiceManager;

    private final OAuthPropertyManager oAuthPropertyManager;

    private Chain commonChain;


    private Chain notCommonChain;

    public OAuthChainManager(OAuthPropertyManager oAuthPropertyManager, ApplicationEventPublisher applicationEventPublisher, OAuthServiceManager oAuthServiceManager) {
        this.oAuthPropertyManager = oAuthPropertyManager;
        this.applicationEventPublisher = applicationEventPublisher;
        this.oAuthServiceManager = oAuthServiceManager;
    }

    public Chain getChain(String type) {
        if (OAuthCorporationType.isCommonType(type)) {
            if (this.commonChain == null) {
                commonChain = new OAuthParamHandler(oAuthPropertyManager);
                commonChain.setChain(new CommonOAuthServiceHandler(oAuthServiceManager))
                           .setChain(new OAuthEventPublisherHandler(applicationEventPublisher));
            }
            return commonChain;
        } else {
            if (this.notCommonChain == null) {
                /**
                 * todo
                 * add apple case
                 */
            }
            return notCommonChain;
        }
    }
}
