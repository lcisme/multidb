package com.example.multidb.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
@EnableJpaRepositories(basePackages = "com.example.multidb.depart.repository",
        entityManagerFactoryRef = "departConfigEntityManager" ,
        transactionManagerRef = "departConfigTransactionManager")
public class DepartmentConfigDatasource {
    @Autowired
    private CommonConfig config;
    //tao ket noi

    @Bean
    public DataSource departmentDataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(config.getDepartmentDriver());
        dataSource.setUrl(config.getDepartmentUrl());
        dataSource.setUsername(config.getDepartmentUsername());
        dataSource.setPassword(config.getDepartmentPassword());
        return dataSource;
    }
    // ket noi

    @Bean("departConfigEntityManager")
    public LocalContainerEntityManagerFactoryBean departConfigEntityManager(){
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(departmentDataSource());
        em.setPackagesToScan(new String[]{"com.example.multidb.depart.entity"});
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(adapter);
        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.dialect", config.getDepartmentDialect());
        properties.put("hibernate.show-sql", config.getDepartmentShowSql());
        em.setJpaPropertyMap(properties);
        return em;
    }

    @Bean("departConfigTransactionManager")
    public PlatformTransactionManager departConfigTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(departConfigEntityManager().getObject());
        return transactionManager;
    }
}