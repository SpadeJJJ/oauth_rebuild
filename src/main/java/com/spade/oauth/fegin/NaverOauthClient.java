package com.spade.oauth.fegin;

import com.google.gson.Gson;
import com.spade.oauth.dto.model.ParamAccessToken;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "a", url = "${oauth2.naver.login.authorize.host.url}")
public interface NaverOauthClient {

    // ?client_id={client_id}&client_secret={client_secret}&grant_type={grant_type}&state={state}&code={code}"
//    @RequestLine("GET /token")
////    @Headers("Content-Type: application/json")
//    String requestAccessTokenCreate(@Param(value = "client_id") String clientId,
//                                  @Param(value = "client_secret") String clientSecret,
//                                  @Param(value = "grant_type") String grantType,
//                                  @Param(value = "state") String state,
//                                  @Param(value = "code") String code);


//    @GetMapping("${oauth2.naver.login.authorize.token.url}")
//    void requestAuthorize(@RequestParam(value = "response_type") String responseType,
//                          @RequestParam(value = "client_id") String clientId,
//                          @RequestParam(value = "redirect_uri") String redirectUri,
//                          @RequestParam(value = "state") String state);


//    @GetMapping("${oauth2.naver.login.authorize.token.url}")
//    String requestAccessTokenCreate(@RequestParam(value = "client_id") String clientId,
//                                  @RequestParam(value = "client_secret") String clientSecret,
//                                  @RequestParam(value = "grant_type") String grantType,
//                                  @RequestParam(value = "state") String state,
//                                  @RequestParam(value = "code") String code);

    @GetMapping("${oauth2.naver.login.authorize.token.url}")
    String requestAccessTokenCreate(@SpringQueryMap ParamAccessToken token);

//    @GetMapping("${oauth2.naver.authorize.request.accesstoken.url3}")
//    Gson requestAccessTokenUpdate(@RequestParam(value = "grant_type") String grantType,
//                                  @RequestParam(value = "client_id") String clientId,
//                                  @RequestParam(value = "client_secret") String clientSecret,
//                                  @RequestParam(value = "refresh_token") String refreshToken);
//
//    @GetMapping("${oauth2.naver.authorize.request.accesstoken.url2}")
//    Gson requestAccessTokenDelete(@RequestParam(value = "grant_type") String grantType,
//                                  @RequestParam(value = "client_id") String clientId,
//                                  @RequestParam(value = "client_secret") String clientSecret,
//                                  @RequestParam(value = "access_token") String accessToken,
//                                  @RequestParam(value = "service_provider") String serviceProvider);



//
//    @GetMapping("${oauth2.naver.authorize.request.accesstoken.url}")
//    Gson requestAccessTokenService(@RequestParam(value = "client_id") String clientId,
//                                   @RequestParam(value = "client_secret") String clientSecret,
//                                   @RequestParam(value = "grant_type") String grantType,
//                                   @RequestParam(value = "state", required = false) String state,
//                                   @RequestParam(value = "code", required = false) String code,
//                                   @RequestParam(value = "refresh_token", required = false) String refreshToken,
//                                   @RequestParam(value = "access_token", required = false) String accessToken,
//                                   @RequestParam(value = "service_provider", required = false) String serviceProvider);
}
