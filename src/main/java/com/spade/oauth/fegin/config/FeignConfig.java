package com.spade.oauth.fegin.config;

import feign.codec.Encoder;
import org.springframework.context.annotation.Bean;

public class FeignConfig {
    @Bean
    Encoder formEncoder() {
        return new feign.form.FormEncoder();
    }
}
