package com.spade.oauth.feign.client;

import com.spade.oauth.dto.param.ParamForAccessToken;
import feign.Headers;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

public interface CommonOAuthClient extends OAuthClient{

}
