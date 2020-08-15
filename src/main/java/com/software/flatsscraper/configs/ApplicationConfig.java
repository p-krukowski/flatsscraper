package com.software.flatsscraper.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;

import javax.sql.DataSource;

@Configuration
@EnableJdbcRepositories("com.software.flatsscraper.repositories")
public
class ApplicationConfig extends AbstractJdbcConfiguration {

    private DataSource dataSource;

    public ApplicationConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}