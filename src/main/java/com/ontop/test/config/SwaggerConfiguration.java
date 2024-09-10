package com.ontop.test.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;


@Configuration
public class SwaggerConfiguration {

    @Value("${spring.application.name}")
    private String appName;

    @Bean
    public OpenAPI myOpenAPI() {



      Info info = new Info()
    	  .description(appName)  
          .title("Test ontop Management API")
          .version("1.0")
          .description("This API exposes endpoints to the test.");
      return new OpenAPI().info(info);
    }
}
