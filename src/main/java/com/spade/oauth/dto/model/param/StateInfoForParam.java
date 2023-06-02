package com.spade.oauth.dto.model.param;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class StateInfoForParam {

    public  StateInfoForParam(String code, String state) {
        this.code = code;
        this.state = state;
    }

    private String code;

    private String state;

    private String refreshToken;

    private String accessToken;
}
