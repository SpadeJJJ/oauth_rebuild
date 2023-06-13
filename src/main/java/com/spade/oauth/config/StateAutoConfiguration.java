package com.spade.oauth.config;


import com.spade.oauth.context.StateContext;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

/**
 * Spring 자동 설정 - State 관리 Context
 */
@AutoConfiguration
@RequiredArgsConstructor
public class StateAutoConfiguration {

    @ConditionalOnMissingBean
    @Bean
    public StateContext stateContext() {
        return new StateContext();
    }
}
