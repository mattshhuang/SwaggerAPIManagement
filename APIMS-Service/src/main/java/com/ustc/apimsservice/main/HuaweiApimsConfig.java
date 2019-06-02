package com.ustc.apimsservice.main;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * initial UI visit: http://localhost:8888/swagger-ui.html;
 * custom  UI visit: http://localhost:8888/docs.html
 * @author matthew huang
 * @description
 * @date 2019/5/22 4:17 PM
 */
@Configuration
@EnableSwagger2
public class HuaweiApimsConfig {
    @Bean
    public Docket BuildDocket(){

        return new Docket(DocumentationType.SWAGGER_2)
                .genericModelSubstitutes(DeferredResult.class)
                .useDefaultResponseMessages(false)
                .forCodeGeneration(false)
                .pathMapping("/")
                .select()
                //.apis(RequestHandlerSelectors.basePackage("com.ustc.apimsservice"))
                .paths(Predicates.not(PathSelectors.regex("/view.*")))
                .paths(Predicates.not(PathSelectors.regex("/index.*")))
                .paths(Predicates.not(PathSelectors.regex("/error.*")))
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {

        Contact contact = new Contact("USTCER", "https://github.com/MatthewHuangs/SwaggerAPIManagement-HUAWEI", "matthew_hs@outlook.com");
        return new ApiInfoBuilder()
                .title("华为API生态管理系统")
                .description("基于Swagger2的API查看、测试、代码生成于一体的API管理系统")
                .termsOfServiceUrl("www.huaweiapimanagementsystem.com")
                .license("© Design & Power by USTC")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.txt")
                .contact(contact)
                .version("1.0.0")
                .build();
    }
}
