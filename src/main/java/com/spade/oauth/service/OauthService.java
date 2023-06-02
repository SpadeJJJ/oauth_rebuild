package com.spade.oauth.service;

import com.spade.oauth.dto.model.param.ParamAccessToken;

// 요청 ? 무슨 요청? 일단 인증 요청 겟이든 뭐든 받아야지? 일단 리퀘스트 어썬티 케이션 - 리턴 없음
// 그 다음 뭐야? 인증 코드 인증 백 유알엘
// 그 다음은 뭐야? 접근 토큰 발급 갱신 삭제 요청
public interface OauthService {

//    String requestAuthorize(Map<String, Object> requestParams);

    String requestAuthorizeTokenUpdate(ParamAccessToken param);

    String requestAuthorizeTokenCreate(ParamAccessToken param);

    String requestAuthorizeTokenDelete(ParamAccessToken param);
}
