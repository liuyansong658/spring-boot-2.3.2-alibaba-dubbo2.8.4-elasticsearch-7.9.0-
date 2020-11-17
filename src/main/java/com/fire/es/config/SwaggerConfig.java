package com.fire.es.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author liuyansong
 * @date 2020/11/03
 */
@EnableSwagger2
@Configuration
public class SwaggerConfig {

    //是否启动swagger，正式环境一般是要关闭的，可根据springboot的多环境配置进行设置
    @Value(value = "${swagger.enabled}")
    Boolean swaggerEnabled;

    @Value(value = "${swagger.title}")
    String title;

    @Autowired
    private Environment environment;

    @Bean
    public Docket createRestApi() {
//        System.out.println("------swaggerEnabled------"+swaggerEnabled);
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                // 是否
                .enable(swaggerEnabled).select()
                // 扫描的路径包
                .apis(RequestHandlerSelectors.basePackage(environment.getProperty("swagger.basePackage")))
                // 指定路径处理PathSelectors.any()有的路径
                .paths(PathSelectors.any()).build().pathMapping("/");
    }

    //设置api信息
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(title)
                .description("lys")
                // 邮件
                .contact(new Contact("lys", "", "liuyansong@weibopay.com"))
                .version("1.0.0")
                .build();
    }
}