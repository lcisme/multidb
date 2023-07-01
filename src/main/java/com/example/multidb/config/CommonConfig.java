package com.example.multidb.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class CommonConfig {
    @Value("${spring.datasource.customer.driver}")
    private String customerDriver;
    @Value("${spring.datasource.customer.url}")
    private String customerUrl;
    @Value("${spring.datasource.customer.username}")
    private String customerUsername;
    @Value("${spring.datasource.customer.passrword}")
    private String customerPassword;
    @Value("${spring.datasource.customer.dialect}")
    private String customerDialect;
    @Value("${spring.datasource.customer.showSql}")
    private String customerShowSql;

    @Value("${spring.datasource.department.driver}")
    private String departmentDriver;
    @Value("${spring.datasource.department.url}")
    private String departmentUrl;
    @Value("${spring.datasource.department.username}")
    private String departmentUsername;
    @Value("${spring.datasource.department.passrword}")
    private String departmentPassword;
    @Value("${spring.datasource.department.dialect}")
    private String departmentDialect;
    @Value("${spring.datasource.department.showSql}")
    private String departmentShowSql;

    @Value("${jms.broker.url}")
    private String jmsBrokerUrl;
    @Value("${jms.broker.username}")
    private String jmsBrokerUsername;
    @Value("${jms.broker.password}")
    private String jmsBrokerPassword;


}
