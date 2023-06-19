package com.spade.oauth.feign.config;

import feign.codec.Encoder;
import feign.form.FormEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Feign 설정 클래스.
 * (Feign Client에서 파라미터 매핑 문제(Snake&Camel)를 위한 인코더 추가)
 *
 * Spring 기본 Encoder 등록
 */
@Configuration
@RequiredArgsConstructor
public class FeignConfig {
    private final ObjectFactory<HttpMessageConverters> messageConverter;

    @Bean
    public Encoder encoder() {
        return new FormEncoder(new SpringEncoder(messageConverter));
    }
}
