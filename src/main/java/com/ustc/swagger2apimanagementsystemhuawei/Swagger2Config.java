package com.ustc.swagger2apimanagementsystemhuawei;

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
 * @Description: step2. swagger2 configure (custom UI), visit: http://localhost:8080/docs.html
 * @Author: matthew huang
 * @Date: 2019/1/26 4:00 PM
 */
@Configuration
@EnableSwagger2
public class Swagger2Config{

    @Bean
    public Docket BuildDocket(){

        return new Docket(DocumentationType.SWAGGER_2)
                    .genericModelSubstitutes(DeferredResult.class)
                    .useDefaultResponseMessages(false)
                    .forCodeGeneration(false)
                    .pathMapping("/")
                    .select()
                    .build()
                    .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {

        return new ApiInfo("华为API生态管理系统",
                "基于Swagger2的API查看、测试、代码生成于一体的API管理系统",
                "1.0.0",
                "API TERMS URL",
                "联系人邮箱",
                "license",
                "license url");
    }
}


///**
// * @Description: step2. swagger2 configure (initial UI), visit: http://localhost:8080/swagger-ui.html
// * @Author: matthew huang
// * @Date: 2019/1/26 3:00 PM
// */
//@Configuration
//@EnableSwagger2
//public class Swagger2Config {
//    @Bean
//    public Docket BuildApi(){
//        return new Docket(DocumentationType.SWAGGER_2)
//                    //调用下面的方法
//                    .apiInfo(apiInfo())
//                    .pathMapping("/")
//                    .select()
//                    /*选择哪些路径和api会生成document*/
//                    //对所有api进行监控
//                    .apis(RequestHandlerSelectors.any())
//                    //错误路径不监控
//                    .paths(Predicates.not(PathSelectors.regex("/error.*")))
//                    //对根下所有路径进行监控
//                    .paths(PathSelectors.regex("/.*"))
//                    .build();
//    }
//
//    private ApiInfo apiInfo() {
//        Contact contact = new Contact("USTCER", "https://github.com/MatthewHuangs/SwaggerAPIManagement-HUAWEI", "ustcer@ustc.edu.cn");
//        return new ApiInfoBuilder()
//                    .title("华为API生态管理系统")
//                    .description("基于Swagger2的API查看、测试、代码生成于一体的API管理系统")
//                    .termsOfServiceUrl("www.huaweiapimanagementsystem.com")
//                    .contact(contact)
//                    .version("1.0.0")
//                    .build();
//    }
//}
