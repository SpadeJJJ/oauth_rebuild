package com.spade.oauth.fegin;

import feign.Contract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeginConfig {
    @Bean
    public Contract useFeignAnnotations() {
        return new Contract.Default();
    }
}
