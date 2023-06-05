package com.spade.oauth.dto.model.param;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParamForStateInfo {

    private ParamForStateInfo(String code) {
        this.code = code;
    }
    private String code;

    private String state;

    private String refreshToken;

    private String accessToken;

    public static ParamForStateInfo toAutorize(String code) {
        ParamForStateInfo result = new ParamForStateInfo();
        result.setCode(code);
        return result;
    }
}
