package com.spade.oauth.feign.config;

import feign.codec.Encoder;
import org.springframework.context.annotation.Bean;

/**
 * Feign 설정 클래스.
 * (Feign Client에서 파라미터 case 매핑 문제(Snake&Camel)를 위한 인코더 추가)
 *
 * 삭제 예정
 */
public class FeignConfig {
    @Bean
    Encoder formEncoder() {
        return new feign.form.FormEncoder();
    }
}
