package com.github.mickeydeeluffy.springsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;

@SpringBootApplication
public class SpringSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityApplication.class, args);
    }

//    @Bean
//    public LocalContainerEntityManagerFactoryBean containerEntityManagerFactoryBean() {
//        LocalContainerEntityManagerFactoryBean mb = new LocalContainerEntityManagerFactoryBean();
//        mb.setDataSource(dataSource());
//        mb.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
//        return mb;
//    }
//    @Bean
//    public DataSource dataSource() {
//        DriverManagerDataSource m = new DriverManagerDataSource();
//        m.setDriverClassName("");
//        return m;
//    }
}
