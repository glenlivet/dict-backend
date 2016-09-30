package org.glenlivet.dict;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ObjectFactoryCreatingFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Application {

    private static final String HEADER_KEY_MASHAPE_KEY = "X-Mashape-Key";
    private static final String HEADER_KEY_ACCEPT = "Accept";

    private static final String BEAN_NAME_WORDS_API_HTTP_ENTITY = "wordsApiHttpEntity";
    public static final String BEAN_NAME_WORDS_API_HTTP_ENTITY_FACTORY = "wordsApiHttpEntityFactory";

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean(BEAN_NAME_WORDS_API_HTTP_ENTITY)
    @Scope("prototype")
    public HttpEntity httpEntity(@Value("${wordsapi.access.token}") String apiKey) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HEADER_KEY_MASHAPE_KEY, apiKey);
        httpHeaders.set(HEADER_KEY_ACCEPT, "application/json");
        HttpEntity entity = new HttpEntity(httpHeaders);
        return entity;
    }

    @Bean(BEAN_NAME_WORDS_API_HTTP_ENTITY_FACTORY)
    public ObjectFactoryCreatingFactoryBean httpEntityFactory() {
        ObjectFactoryCreatingFactoryBean factoryBean = new ObjectFactoryCreatingFactoryBean();
        factoryBean.setTargetBeanName(BEAN_NAME_WORDS_API_HTTP_ENTITY);
        return factoryBean;
    }

}