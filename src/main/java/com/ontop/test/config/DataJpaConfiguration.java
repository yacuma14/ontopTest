package com.ontop.test.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EntityScan("com.ontop.test.entity")
@EnableJpaRepositories("com.ontop.test.repository")
@EnableTransactionManagement
public class DataJpaConfiguration {

}
