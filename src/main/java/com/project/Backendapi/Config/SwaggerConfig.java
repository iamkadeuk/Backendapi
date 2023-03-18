package com.project.Backendapi.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    // localhost:8088/v2/api-docs
    // localhost:8088/swagger-ui/index.html/

    // Swagger Documentation
    private static final Contact DEFAULT_CONTACT = new Contact("허가득",
            "https://github.com/iamkadeuk", "iamkadeuk@icloud.com");

    private static final ApiInfo DEFAULT_API_INFO = new ApiInfo("블로그 검색",
            "블로그 검색", "1.0", "urn:tos", DEFAULT_CONTACT,
            "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0", new ArrayList<>());

    private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES = new HashSet<>(
            Arrays.asList("application/json", "application/xml")); // json과 xml 파일 형식을 지원함

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(DEFAULT_API_INFO)
                .produces(DEFAULT_PRODUCES_AND_CONSUMES)
                .consumes(DEFAULT_PRODUCES_AND_CONSUMES);
    }
}