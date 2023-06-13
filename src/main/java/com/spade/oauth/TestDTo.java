package com.spade.oauth;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
//@ConfigurationProperties(prefix = "test")
//@Component
@ToString
public class TestDTo {
    private String test1;
    private String test2;
}
