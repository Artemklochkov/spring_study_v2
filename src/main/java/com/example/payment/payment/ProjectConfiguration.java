package com.example.payment.payment;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class ProjectConfiguration {
    @Value("${custom.datasource.url}")
    private String datasourceUrl;

    @Value("${custom.datasource.dbName}")
    private String datasourcedbName;

    @Value("${custom.datasource.username}")
    private String datasourceUserName;

    @Value("${custom.datasource.password}")
    private String datasourcePass;

    @Value("${custom.datasource.url.hikari}")
    private String datasourceUrlHikari;

    @Bean
    public DataSource dataSource(){
        /*PGSimpleDataSource dataSource = new PGSimpleDataSource ();
            dataSource.setServerName(datasourceUrl);
            dataSource.setDatabaseName(datasourcedbName);
            dataSource.setUser(datasourceUserName);
            dataSource.setPassword(datasourcePass);
//            dataSource.setConnectionTimeout(1000);
            return dataSource;*/
        /*HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(datasourceUrlHikari);
        hikariConfig.setUsername(datasourceUserName);
        hikariConfig.setPassword(datasourcePass);
        hikariConfig.setDriverClassName("org.postgresql.Driver");
        hikariConfig.setConnectionTimeout(1000);
        HikariDataSource dataSource = new HikariDataSource(hikariConfig);
            return dataSource;*/
//        return new HikariDataSource(hikariConfig);

        HikariDataSource dataSource = new HikariDataSource();
            dataSource.setJdbcUrl(datasourceUrlHikari);
            dataSource.setUsername(datasourceUserName);
            dataSource.setPassword(datasourcePass);
            dataSource.setDriverClassName("org.postgresql.Driver");
            dataSource.setConnectionTimeout(1000);
            return dataSource;
    }

}
