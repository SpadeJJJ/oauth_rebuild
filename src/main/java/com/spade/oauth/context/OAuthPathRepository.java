package com.spade.oauth.context;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * CallBack url 관리용 Repository
 *
 * todo.
 * 변경 예정
 */
@Component
@RequiredArgsConstructor
public class OAuthPathRepository {

    @Value("${oauth2.authorize.callback.urls}")
    private final List<String> paths;

    public List<String> getOAuthPaths() {
        return this.paths;
    }
}
