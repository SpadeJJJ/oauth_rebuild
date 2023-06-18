package com.spade.oauth;

import com.spade.oauth.context.OAuthPathContext;
import com.spade.oauth.dto.model.param.ParamForAccessToken;
import feign.Contract;
import feign.Feign;
import feign.codec.Encoder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.web.bind.annotation.*;


//@RequiredArgsConstructor
@RestController
@RequiredArgsConstructor
public class FooController {

//    private final Decoder decoder;
//
//    private final Encoder encoder;
//
//    private final Client client;
//
//    private final Contract contract;

    private final ObjectFactory<HttpMessageConverters> messageConverter;

    private final Encoder encoder;



    @GetMapping("/{type}")
    public String set(@PathVariable("type") String type){
//        String state = stateUtilService.createStateForOAuth(type);

        String state = "111111111111";
//        stateUtilService.save(state, "naver");


        String url = "https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=UMKT4dIs6FAj23xkYaC1&redirect_uri=http://localhost:8080/" + type + "/callback&state="+state;

        return url;
    }


    public Contract getC(){
        return new feign.Contract.Default();
    }

    public String a() {
//        Encoder encoder = new Encoder.Default();
//        Decoder decoder = new Decoder.Default();

//        SpringDecoder springDecoder = new SpringDecoder();

        String result= "";

        try {
//            this.kk = Feign.builder().contract(new SpringMvcContract()).encoder(encoder).decoder(decoder)
//                           .target(KK.class, "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code");
//
//            ParamForAccessToken param = new ParamForAccessToken();
//        param.setState("1111111");
//        param.setCode("dsfsefewsgsegsegewg");


//            KK2 temp = Feign.builder()
//                    .contract(new SpringMvcContract())
//                    .encoder(encoder)
//                    .target(KK2.class, "https://nid.naver.com/oauth2.0");



//            this.kk = Feign.builder()
//                           .contract(new SpringMvcContract())
//                           .encoder(encoder)
//                            .target(KK.class, "https://nid.naver.com/oauth2.0");


//            ParamForAccessToken param = new ParamForAccessToken();
//            param.setGrantType("authorization_code");
//            param.setClientId("UMKT4dIs6FAj23xkYaC1");
//            param.setClientSecret("fwBz2BIrLyf");
//            param.setState("111111111111");
//            param.setCode("1dfwefwefwef");
//
////            result= kk.requestAccessTokenCreate2(param);
//            result = temp.requestAccessTokenCreate2(param);
//            System.out.println("?? "+result);
        } catch (Exception e) {
            System.out.println("hi "+e.getMessage());
            return e.getMessage();
        }
        return result;
    }

//    public static class ParamCreateUtil {
//
//        private static final String PROPERTIES_PROFIX = "oauth2.authorize-info.";
//        private static final String CALL_BACL_URL = ".call-back-url";
//        private static final String GRANT_TYPE = ".grant-type";
//        private static final String CLIENT_ID = ".client-id";
//        private static final String SECRET_KEY = ".secret-key";
//        private static final String URL = ".host-url";
//
//        public static void createAccessTokenParam(String type, ParamForAccessToken param, OAuthPathContext oAuthPathContext) {
//
//            if (type.equals("naver")) {
//                oAuthPathContext.print();
//                System.out.println("check key "+PROPERTIES_PROFIX+type+GRANT_TYPE);
//                param.setClientId(oAuthPathContext.findProperties(PROPERTIES_PROFIX+type+CLIENT_ID));
//                param.setGrantType(oAuthPathContext.findProperties(PROPERTIES_PROFIX+type+GRANT_TYPE));
//                param.setClientSecret(oAuthPathContext.findProperties(PROPERTIES_PROFIX+type+SECRET_KEY));
//                System.out.println("param garnt "+param.getGrantType());
//            } else if(type.equals("kakao")) {
//                param.setClientId(oAuthPathContext.findProperties(PROPERTIES_PROFIX+type+CLIENT_ID));
//                param.setGrantType(oAuthPathContext.findProperties(PROPERTIES_PROFIX+type+GRANT_TYPE));
//            }
//        }
//
//        public static String getUrlForAccessToken(String type, OAuthPathContext oAuthPathContext) {
//            System.out.println("what fuck "+PROPERTIES_PROFIX+type+URL);
//            System.out.println("what fuck result "+oAuthPathContext.findProperties(PROPERTIES_PROFIX+type+URL));
//            return oAuthPathContext.findProperties(PROPERTIES_PROFIX+type+URL);
//        }
//    }
}
