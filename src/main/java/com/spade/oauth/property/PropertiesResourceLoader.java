package com.spade.oauth.property;

import com.spade.oauth.OAuthCorporationType;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.context.support.StandardServletEnvironment;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

/**
 * 모든 properties Environment 등록 및 @Source 등록.
 */
@Configuration
@RequiredArgsConstructor
public class PropertiesResourceLoader {

    private final StandardServletEnvironment standardServletEnvironment;

    /** property를 @Source로 등록
     * 이거 삭제 예정*/
    @Bean(name = "properties")
    public PropertiesFactoryBean propertiesFactoryBean() throws IOException {
        PropertiesFactoryBean bean = new PropertiesFactoryBean();

        /** classpath url */
        String path = ResourceUtils.getFile("classpath:application.properties").getAbsolutePath().replace("application.properties", "").replace("\\", "/") + "config";
        File file = new File(path);
        String[] filesName = file.list();

        ClassPathResource[] classPathResources = new ClassPathResource[filesName.length+1];
        for (int i = 0; i < filesName.length; i++) {
            classPathResources[i] = new ClassPathResource("/config/"+filesName[i]);
        }
        classPathResources[classPathResources.length-1] = new ClassPathResource("application.properties");
        bean.setLocations(classPathResources);

        return bean;
    }

    /** property Environment에 등록 */
    @PostConstruct
    public void setUp() throws IOException {
        /** classpath url */
        String path = ResourceUtils.getFile("classpath:application.properties").getAbsolutePath().replace("application.properties", "").replace("\\", "/")+"config";
        File file = new File(path);
        String[] filesName = file.list();

        for (String name : filesName) {
            String typeName = name.replace(".properties", "");
            OAuthCorporationType.valueOf(typeName.toUpperCase()).setIsLoad(true);
            Properties properties = new Properties();
            Resource resource = new ClassPathResource("/config/"+name);
            PropertiesLoaderUtils.fillProperties(properties, resource);
            standardServletEnvironment.getPropertySources().addLast(new PropertiesPropertySource(name, properties));
        }
    }
}
