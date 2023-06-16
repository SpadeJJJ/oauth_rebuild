package com.spade.oauth;

import com.spade.oauth.dto.model.param.ParamForAccessToken;
import feign.Contract;
import feign.Feign;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.form.FormEncoder;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.FeignClientProperties;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.context.annotation.Import;
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
    private KK kk;



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

    @GetMapping("/tt")
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




            this.kk = Feign.builder()
                           .contract(new SpringMvcContract())
                           .encoder(encoder)
                           .target(KK.class, "https://nid.naver.com/oauth2.0/token");


            ParamForAccessToken param = new ParamForAccessToken();
            param.setGrantType("authorization_code");
            param.setClientId("UMKT4dIs6FAj23xkYaC1");
            param.setClientSecret("fwBz2BIrLyf");
            param.setState("111111111111");
            param.setCode("1dfwefwefwef");

            result= kk.requestAccessTokenCreate2(param);
            System.out.println("?? "+result);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return e.getMessage();
        }
        return result;
    }

}
