package com.example.web.config;

import com.example.bean.AppProperties;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Arrays;
import java.util.Collection;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
@ComponentScan(basePackages = {"com.example.service", "com.example.dao"})
public class BeanConfig {

    @Autowired
    private Environment env;

    @Bean
    public AppProperties newAppProperties() {
        String name = env.getProperty("app.name");
        String serverName = env.getProperty("app.serverName");
        String toAddressesString = env.getProperty("app.toAddresses");
        Collection<String> toAddresses = Arrays.asList(toAddressesString.split("\\s*,\\s*"));
        return new AppProperties(name, serverName, toAddresses);
    }

    @Bean
    public BasicDataSource dataSource() {
        String driverClassName = env.getProperty("database.driverClassName");
        String username = env.getProperty("database.username");
        String password = env.getProperty("database.password");
        String jdbcUrl = env.getProperty("database.jdbcUrl");

        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(jdbcUrl);
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        dataSource.setValidationQuery("SELECT 1");
        dataSource.setValidationQueryTimeout(1);
        dataSource.setTestOnBorrow(true);
        dataSource.setTestWhileIdle(true);

        return dataSource;
    }

    @Bean
    public DataSourceTransactionManager transactionManager() {
        BasicDataSource dataSource = dataSource();
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public JdbcOperations jdbcOperations() {
        BasicDataSource dataSource = dataSource();
        return new JdbcTemplate(dataSource);
    }
}
