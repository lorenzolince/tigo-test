/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tigo.test.config;

import java.util.HashMap;
import java.util.Map;
import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

/**
 *
 * @author loren
 */
@Configuration
@EnableJpaRepositories(basePackages = "com.tigo.test.repository")
public class ApplicationConfig {

    @Value("${spring.jpa.properties.hibernate.dialect}")
    private String dialect;
    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String dbAuto;
    @Value("${spring.jpa.show-sql}")
    private String showSwl;

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }

    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(@Qualifier("dataSource") DataSource datasource, EntityManagerFactoryBuilder builder) {
        Map<String, Object> properties = new HashMap<>();
        properties.put("spring.jpa.show-sql", showSwl);
        properties.put("hibernate.hbm2ddl.auto", dbAuto);
        properties.put("hibernate.dialect", dialect);
        return builder.dataSource(datasource)
                .packages("com.tigo.test.model")
                .properties(properties)
                .persistenceUnit("mariaDB")
                .build();

    }

    @Bean
    public SpringLiquibase liquibase(@Qualifier("dataSource") DataSource datasource,  @Value("${liquibase.shouldRun}") boolean shouldRun
    ) throws IllegalArgumentException, NamingException {
        SpringLiquibase liquibase = new SpringLiquibase();
        try {
            String defaultSchema = datasource.getConnection().getCatalog(); 
            System.setProperty("defaultSchemaName", defaultSchema);
            String changelogFile = "classpath:/database/module-database.xml";
            liquibase.setChangeLog(changelogFile);
            liquibase.setDefaultSchema(defaultSchema);
            liquibase.setDataSource(datasource);
            liquibase.setDropFirst(false);
            liquibase.setContexts(defaultSchema);
            liquibase.setShouldRun(shouldRun);
            Map<String, String> params = new HashMap<>();
            params.put("verbose", "true");
            liquibase.setChangeLogParameters(params);
        } catch (Exception e) {
            System.out.println(" Exception: " + e.getMessage());
        }
        return liquibase;
    }
}
