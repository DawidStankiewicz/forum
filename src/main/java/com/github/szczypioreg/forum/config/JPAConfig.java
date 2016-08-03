/**
 * Created by Dawid Stankiewicz on 11.07.2016
 */
package com.github.szczypioreg.forum.config;

import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.github.szczypioreg.forum.domain")
public class JPAConfig {
    
    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean getEntityManagerFactory(DataSource dataSource,
            JpaVendorAdapter jpaVendorAdapter) {
        LocalContainerEntityManagerFactoryBean emfb = new LocalContainerEntityManagerFactoryBean();
        emfb.setDataSource(dataSource);
        emfb.setJpaVendorAdapter(jpaVendorAdapter);
        emfb.setPackagesToScan("com.github.szczypioreg.forum.domain");
        return emfb;
    }
    
    @Bean(destroyMethod = "shutdown")
    @Profile("development")
    public DataSource embeddedDataSource() throws Exception {
        System.out.println("DataSource in dev");
        return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.HSQL).addScript(
                "classpath:database/schema.sql").addScript("classpath:database/test-data.sql")
                .build();
    }
    
    @Bean
    @Profile("production")
    public DataSource getDataSource() throws Exception {
        System.out.println("DataSource in prod");
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/forum");
        dataSource.setUsername("root");
        dataSource.setPassword("");
        return dataSource;
    }
    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabase(Database.MYSQL);
        adapter.setShowSql(true);
        adapter.setGenerateDdl(false);
        adapter.setDatabasePlatform("org.hibernate.dialect.MySQLDialect");
        adapter.setPrepareConnection(false);
        return adapter;
    }
    
    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManagerJPA(
            EntityManagerFactory entityManagerFactory) throws NamingException {
        JpaTransactionManager txManager = new JpaTransactionManager(entityManagerFactory);
        return txManager;
    }
    
}
