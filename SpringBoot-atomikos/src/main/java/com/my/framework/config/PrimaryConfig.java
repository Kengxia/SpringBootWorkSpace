package com.my.framework.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 华安  mashuai_bj@si-tech.com.cn
 * @Title:
 * @Date: Create in 10:10 2018/7/25
 * @Description:
 */
@Configuration
@EnableJpaRepositories(
        entityManagerFactoryRef = "entityManagerFactoryPrimary",
        transactionManagerRef = "transactionManager",
        basePackages = {"com.my.work.repositories.primary"},
        excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = Entity.class)})
public class PrimaryConfig {

    @Resource
    @Qualifier("primaryDataSource")
    private DataSource primaryDataSource;

    @Autowired
    private JpaVendorAdapter jpaVendorAdapter;

    /**
     * 设置实体类所在位置
     */
    @Primary
    @Bean(name = "entityManagerFactoryPrimary")
    @DependsOn("transactionManager")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryPrimary() {

        HashMap<String, Object> properties = new HashMap<String, Object>();
        properties.put("hibernate.transaction.jta.platform", AtomikosJtaPlatform.class.getName());
        properties.put("javax.persistence.transactionType", "JTA");
        LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
        entityManager.setJtaDataSource(primaryDataSource);
        entityManager.setJpaVendorAdapter(jpaVendorAdapter);
        entityManager.setPackagesToScan("com.my.work.repositories.primary.pojo");
        entityManager.setPersistenceUnitName("customerPersistenceUnit");
        entityManager.setJpaPropertyMap(properties);

        return entityManager;
    }



} 