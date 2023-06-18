package com.spade.oauth;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resources;
import lombok.RequiredArgsConstructor;
import org.aspectj.apache.bcel.util.ClassPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.boot.env.OriginTrackedMapPropertySource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.context.support.StandardServletEnvironment;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Map;
import java.util.Properties;


@Configuration
@RequiredArgsConstructor
public class PropertiesResourceConfig {

    @Autowired
    private final StandardServletEnvironment standardServletEnvironment;

    @Autowired
    private final Environment environment;

    @Bean(name = "properties")
    public PropertiesFactoryBean mapper() throws IOException {
        System.out.println("call ");
        PropertiesFactoryBean bean = new PropertiesFactoryBean();

        String path = ResourceUtils.getFile("classpath:application.properties").getAbsolutePath().replace("application.properties", "").replace("\\", "/") + "config";
        File file = new File(path);
        String[] filesName = file.list();

        ClassPathResource[] classPathResources = new ClassPathResource[filesName.length+1];
        for (int i = 0; i < filesName.length; i++) {
            System.out.println(classPathResources.length+ " vs "+i);
            classPathResources[i] = new ClassPathResource("/config/"+filesName[i]);
        }
        classPathResources[classPathResources.length-1] = new ClassPathResource("application.properties");
        bean.setLocations(classPathResources);

        return bean;
    }

    @PostConstruct
    public void setUp() throws IOException {
        String path = ResourceUtils.getFile("classpath:application.properties").getAbsolutePath().replace("application.properties", "").replace("\\", "/")+"config";
        System.out.println("? "+path);
        File file = new File(path);
        String[] filesName = file.list();

//        PropertySource testP = standardServletEnvironment.getPropertySources().get("Config resource 'class path resource [application.properties]' via location 'optional:classpath:/'");

        for (String name : filesName) {
            Properties properties = new Properties();
            System.out.println(name);
            Resource resource = new ClassPathResource("/config/"+name);
            PropertiesLoaderUtils.fillProperties(properties, resource);
            standardServletEnvironment.getPropertySources().addLast(new PropertiesPropertySource(name, properties));
        }

//        standardServletEnvironment.getPropertySources().addLast(new PropertiesPropertySource("test", properties));
//        System.out.println("check1 "+standardServletEnvironment.getPropertySources().get("test").containsProperty("oauth2.authorize-info.kakao.client-id"));
//        System.out.println("check2 "+standardServletEnvironment.getPropertySources().get("test").containsProperty("oauth2.authorize-info.naver.secret-key"));
//        System.out.println("check3 "+standardServletEnvironment.getPropertySources().get("test").getSource().toString());
//        standardServletEnvironment.getPropertySources().addFirst(new OriginTrackedMapPropertySource("Config resource 'class path resource [application.properties]' via location 'optional:classpath:/'", properties));

    }
}
