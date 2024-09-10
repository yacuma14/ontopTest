package com.ontop.test.main;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;


@SpringBootApplication(scanBasePackages = {
        "com.ontop.test.*"
})
@ConfigurationPropertiesScan(basePackages = {
        "com.ontop.test.config"
})

public class OntopApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(OntopApplication.class).build().run(args);
    }


}
