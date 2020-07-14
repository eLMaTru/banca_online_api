package com.bancaonline.api.configuration;

import java.util.Collections;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * The type Swagger config.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurerAdapter {

    /**
     * Api docket.
     *
     * @return the docket
     */
    @Bean
    public Docket api() {

        return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
                .apis(Predicates.not(RequestHandlerSelectors.basePackage("org.springframework.boot")))
                .paths(PathSelectors.any()).build().apiInfo(apiInfo());

    }

    /**
     * addResourceHandlers
     *
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // enabling swagger-ui part for visual documentation
        registry.addResourceHandler("/swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    private ApiInfo apiInfo() {
        return new ApiInfo("Banca-Online REST API",
                "This Rest API provide some useful endpoints to performs some operation related to lotteries results.",
                "API v1.0", "Please read ours Terms of Service",
                new Contact("Jorge de los santos", "@jorgeldlslopez", "jorgeluisdelossantoslopez@gmail.com"),
                "License of API", "API license URL", Collections.emptyList());
    }



}
