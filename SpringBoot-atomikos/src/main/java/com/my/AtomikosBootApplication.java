package com.my;

import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;
import com.my.framework.config.AtomikosJtaPlatform;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;

/**
 * @author 华安  mashuai_bj@si-tech.com.cn
 * @Title:
 * @Date: Create in 10:17 2018/4/17
 * @Description:
 */
@SpringBootApplication
@EnableTransactionManagement
public class AtomikosBootApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(AtomikosBootApplication.class, args);
    }



    @Bean(name = "userTransaction")
    public UserTransaction userTransaction() throws Throwable {
        UserTransactionImp userTransactionImp = new UserTransactionImp();
        userTransactionImp.setTransactionTimeout(1000);
        return userTransactionImp;
    }

    @Bean(name = "atomikosTransactionManager", initMethod = "init", destroyMethod = "close")
    public TransactionManager atomikosTransactionManager() throws Throwable {
        UserTransactionManager userTransactionManager = new UserTransactionManager();
        userTransactionManager.setForceShutdown(true);

        AtomikosJtaPlatform.transactionManager = userTransactionManager;

        return userTransactionManager;
    }



    @Bean(name = "transactionManager")
    @DependsOn({ "userTransaction", "atomikosTransactionManager" })
    public JtaTransactionManager transactionManager() throws Throwable {
        UserTransaction userTransaction = userTransaction();

        AtomikosJtaPlatform.transaction = userTransaction;

        TransactionManager atomikosTransactionManager = atomikosTransactionManager();

        return new JtaTransactionManager(userTransaction, atomikosTransactionManager);
    }
} 