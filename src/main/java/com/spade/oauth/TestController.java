package com.spade.oauth;

import com.spade.oauth.context.OAuthPathContext;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
public class TestController {
   @Resource(name = "properties")
   private Map<String, String> properties;

   @Value("${oauth2.authorize-info.kakao.host-url}")
   private String a;

   @Autowired
   Environment environment;


    @GetMapping("/test")
    public String test(){
        String callBackKey = "[oauth2].[authorize\\-info].[a-z.+].[call\\-back\\-url]";
        Pattern pattern = Pattern.compile(callBackKey);
        Matcher matcher = pattern.matcher("oauth2.authorize-info.kakao.call-back-url");
        System.out.println("find? "+matcher.find());


        String callBackKey2 = "(\\boauth2.authorize-info.\\b)(.*)(\\b.call-back-url\\b)";
        Pattern pattern2 = Pattern.compile(callBackKey2);
        Matcher matcher2 = pattern2.matcher("oauth2.authorize-info.kakao.call-back-url");

        System.out.println("count "+matcher2.groupCount()+" "+matcher2.find()+ " ? "+matcher2.group(2));
//        System.out.println("gr "+matcher1.group(1)+"gr "+matcher1.group(2)+"gr " );
//        while (matcher1.find()) {
//            System.out.println("group "+matcher1.group());
//        }



        Pattern pattern3 = Pattern.compile("(\\b특정단어\\b)(.*?)(\\b특정단어\\b)");
        Matcher matcher3 = pattern3.matcher("특정단어 문자열 사이의 값 특정단어 추출하기");
        if (matcher3.find()){
            System.out.println(matcher3.group(2).trim()); // 특정 단어 사이의 값 추출
        }


//        OAuthPathContext OAuthPathContext = new OAuthPathContext();
//        OAuthPathContext.setProperties(properties);
//        System.out.println("set "+ OAuthPathContext.getOAuthPathMap().keySet());;

//
//        System.out.println(a);
//        System.out.println("=====================================================");
//        System.out.println("key and value");
//        for (String key : properties.keySet()) {
//            System.out.println(key + "  :  "+properties.get(key) );
//        }
//        System.out.println("=====================================================");
//
//
//
//        /** callback 가정. */
//        String callback = "/naver/callback";
//
//        /** properties에 모든 key value를 받음. */
//        for (String key : properties.keySet()) {
//
//            /** callback url과 같은 경우 == /naver/callback  */
//            if (callback.equals(properties.get(key))) {
//
//                /**
//                 * enum 값들 중에서 name과 key를 비교해서
//                 * key에 name이 contains 해서 값이 있으면 리턴
//                 * ( key = oauth2.authorize.naver.call-back-url && name = {naver, kakao, ...} )
//                 */
//                TestEnum result = TestEnum.getType(key);
//                System.out.println("result : "+result.getName());
//            }
//        }

        return "";
    }
}
