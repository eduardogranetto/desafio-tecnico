package br.com.desafiotecnico.config;

import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(WebConfig.DEFAULT_PACKAGE)
@EntityScan(WebConfig.DEFAULT_PACKAGE)
public class JPAConfig {
}