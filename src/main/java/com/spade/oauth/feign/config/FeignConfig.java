package com.spade.oauth.feign.config;

import feign.codec.Encoder;
import feign.form.FormEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Feign 설정 클래스.
 * (Feign Client에서 파라미터 case 매핑 문제(Snake&Camel)를 위한 인코더 추가)
 *
 */
@Configuration
@RequiredArgsConstructor
public class FeignConfig {
//    @Bean
//    Encoder formEncoder() {
//        return new feign.form.FormEncoder();
//    }

    private final ObjectFactory<HttpMessageConverters> messageConverter;

    @Bean
    public Encoder encoder() {
        return new FormEncoder(new SpringEncoder(messageConverter));
    }
}
