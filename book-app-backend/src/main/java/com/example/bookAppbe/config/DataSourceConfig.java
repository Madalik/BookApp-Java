package com.example.bookAppbe.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Bean
    public DataSource dataSource() {
        DataSourceBuilder<HikariDataSource> dataSourceBuilder = DataSourceBuilder.create().type(HikariDataSource.class);
        dataSourceBuilder.driverClassName("org.postgresql.Driver");
        dataSourceBuilder.url("jdbc:postgresql://localhost:5432/postgres");
        dataSourceBuilder.username("postgres");
        dataSourceBuilder.password("postgres");
        dataSourceBuilder.type(HikariDataSource.class);
        return dataSourceBuilder.build();
    }

}
