package com.spade.oauth.config;

import com.spade.oauth.service.kakao.KakaoRequestParamCreateService;
import com.spade.oauth.service.naver.NaverRequestParamCreateService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * Spring 자동 설정 - param 관련 서비스
 */
@AutoConfiguration
@RequiredArgsConstructor
public class ParamServiceAutoConfiguration {

    @Bean
    public NaverRequestParamCreateService naverParamService() {
        return new NaverRequestParamCreateService();
    }

    @Bean
    public KakaoRequestParamCreateService kakaoParamService() {
        return new KakaoRequestParamCreateService();
    }
}
