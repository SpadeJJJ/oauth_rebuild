package com.spade.oauth;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resources;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.web.context.support.StandardServletEnvironment;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;


@Configuration
@RequiredArgsConstructor
public class PropertiesResourceConfig {

    @Bean(name = "properties")
    public static PropertiesFactoryBean mapper() {
        System.out.println("call ");
        PropertiesFactoryBean bean = new PropertiesFactoryBean();
//        bean.setLocation(new ClassPathResource("application.properties"));

        String path = "./src/main/resources/config";
        File file = new File(path);
        String[] filesName = file.list();

//        System.out.println(filesName.length);

        ClassPathResource[] classPathResources = new ClassPathResource[filesName.length+1];
        for (int i = 0; i < filesName.length; i++) {
            System.out.println(classPathResources.length+ " vs "+i);
            classPathResources[i] = new ClassPathResource("/config/"+filesName[i]);
        }
        classPathResources[classPathResources.length-1] = new ClassPathResource("application.properties");
        bean.setLocations(classPathResources);

        return bean;
    }

    @Autowired
    private final StandardServletEnvironment standardServletEnvironment;

    @PostConstruct
    public void setUp() throws IOException {
        System.out.println("setUp call");
        String path = "./src/main/resources/config";
        File file = new File(path);
        String[] filesName = file.list();
        for (String name : filesName) {
            Resource resource = new FileSystemResource(path+"/"+name);
            Properties properties = new Properties();
            PropertiesLoaderUtils.fillProperties(properties, resource);
            standardServletEnvironment.getPropertySources().addLast(new PropertiesPropertySource(name, properties));
        }
    }
}
