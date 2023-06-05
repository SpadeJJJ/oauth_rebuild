package com.spade.oauth.util;

import jakarta.servlet.http.HttpSession;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * Test용 State 생성
 **/
public class OauthStateUtil {

    public static String createState() {
        SecureRandom random = new SecureRandom();
        String state = new BigInteger(130, random).toString();
        return state;
    }

}
