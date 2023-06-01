package com.spade.oauth.util;

import java.math.BigInteger;
import java.security.SecureRandom;

public class OauthStateUtil {

    public static String createState() {
        SecureRandom random = new SecureRandom();
        String state = new BigInteger(130, random).toString();

        return state;
    }
}
