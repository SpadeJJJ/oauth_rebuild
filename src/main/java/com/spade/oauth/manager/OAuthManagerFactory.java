package com.spade.oauth.manager;

import com.spade.oauth.service.AuthorizeRequestUrlService;
import feign.codec.Encoder;
import jakarta.annotation.PostConstruct;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
//@DependsOn(value = {"environment"})
@DependsOn(value = {"propertiesResourceLoader"})
public class OAuthManagerFactory {

    private final Environment environment;

    private final Encoder encoder;

    private final ApplicationEventPublisher applicationEventPublisher;

    private OAuthPropertyManager oAuthPropertyManager;
    private OAuthServiceManager oAuthServiceManager;
    private OAuthChainManager oAuthChainManager;

    public OAuthManagerFactory(Environment environment, Encoder encoder, ApplicationEventPublisher applicationEventPublisher) {
        this.environment = environment;
        this.encoder = encoder;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @PostConstruct
    public void init() {
        setOAuthPropertyManager();
        setOAuthServiceManager();
        setOAuthChainManager();
    }

    public void setOAuthPropertyManager() {
        this.oAuthPropertyManager = new OAuthPropertyManager(environment);
    }

    public void setOAuthServiceManager() {
        this.oAuthServiceManager = new OAuthServiceManager(oAuthPropertyManager, encoder);
    }

    public void setOAuthChainManager() {
        this.oAuthChainManager = new OAuthChainManager(oAuthPropertyManager, applicationEventPublisher, oAuthServiceManager);
    }

    public OAuthPropertyManager getOAuthPropertyManager() {
        return oAuthPropertyManager;
    }
    public OAuthServiceManager getOAuthServiceManager() {
        return oAuthServiceManager;
    }
    public OAuthChainManager getOAuthChainManager() {
        return oAuthChainManager;
    }

    @Bean
    @PostConstruct
    public AuthorizeRequestUrlService authorizeRequestUrlService(){
        return new AuthorizeRequestUrlService(oAuthPropertyManager);
    }
}
