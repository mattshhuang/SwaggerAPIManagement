package com.ustc.apimsservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.util.ResourceUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@SpringBootApplication
public class ApimsServiceApplication extends WebMvcConfigurationSupport {

    public static void main(String[] args) {

        SpringApplication.run(ApimsServiceApplication.class, args);
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/webjars/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX + "/webjars/");
        super.addResourceHandlers(registry);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
