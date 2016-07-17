/**
 * Created by Dawid Stankiewicz on 11.07.2016
 */
package com.github.szczypioreg.forum.config;

import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class JPAConfig {
    
    @Bean
    public LocalContainerEntityManagerFactoryBean getEntityManagerFactory(DataSource dataSource,
            JpaVendorAdapter jpaVendorAdapter) {
        LocalContainerEntityManagerFactoryBean emfb = new LocalContainerEntityManagerFactoryBean();
        emfb.setDataSource(dataSource);
        emfb.setJpaVendorAdapter(jpaVendorAdapter);
        emfb.setPackagesToScan("com.github.szczypioreg.forum.domain");
        return emfb;
    }
    
    @Bean
    public DataSource getDataSource() throws Exception {
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
    
    @Bean
    public PlatformTransactionManager
           transactionManagerJPA(EntityManagerFactory entityManagerFactory) throws NamingException {
        JpaTransactionManager txManager = new JpaTransactionManager(entityManagerFactory);
        return txManager;
    }
    
}
