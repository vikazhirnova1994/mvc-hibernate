package com.project.config;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

/**
 * @author Victoria Zhirnova
 * @project mvc-hibernate
 */

@Configuration
@PropertySource("classpath:liquibase.properties")
public class LiquibaseConfig {

    @Value("${spring.liquibase.changeLogFile}")
    private String changeLog;

    @Bean(name = "liquibase")
    public SpringLiquibase springLiquibase(DataSource dataSource) {
        SpringLiquibase springLiquibase = new SpringLiquibase();

        springLiquibase.setDataSource(dataSource);
        springLiquibase.setChangeLog(changeLog);
        return springLiquibase;
    }
}
