package com.io.v1.exportapp.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "cryptoEntityManagerFactory",
                        basePackages = {"com.io.v1.exportapp.repo"},
                        transactionManagerRef = "cryptoTransactionManager")
public class DbConfig {
    private static final Logger log = LoggerFactory.getLogger(DbConfig.class);

    @Bean(name = "pgDS")
    @ConfigurationProperties(prefix = "db.pg")
    public DataSource getPGDs(){
        log.info("\nInitialized PostGres - DS : ");
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "cryptoEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean eM(@Qualifier("pgDS") DataSource ds){
        LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
        bean.setDataSource(ds);
        JpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        bean.setJpaVendorAdapter(adapter);
        HashMap<String,Object> properties = new HashMap<String, Object>();
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        bean.setJpaPropertyMap(properties);
        bean.setPackagesToScan("com.io.v1.exportapp.model");
        return bean;
    }

    @Bean(name = "cryptoTransactionManager")
    public PlatformTransactionManager transactionManager(@Qualifier("cryptoEntityManagerFactory") EntityManagerFactory entityManagerFactory ) {
        log.info("\nInitialized JPA implementatiom : " );
        return new JpaTransactionManager(entityManagerFactory);
    }

    @Bean(name = "pgTemplate")
    public JdbcTemplate getTemplate(@Qualifier("pgDS")DataSource ds) {
        log.info("\nInitialized JDBC Template : " );
        return new JdbcTemplate(ds);
    }
}
