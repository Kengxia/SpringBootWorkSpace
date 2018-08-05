package com.my.framework.config;

import com.alibaba.druid.pool.xa.DruidXADataSource;
import com.atomikos.jdbc.AtomikosDataSourceBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.servlet.annotation.WebServlet;
import javax.sql.DataSource;
import javax.sql.XADataSource;
import java.sql.SQLException;


/**
 * @author 华安  mashuai_bj@si-tech.com.cn
 * @Title:
 * @Date: Create in 9:41 2018/7/25
 * @Description:
 */

@Configuration
@WebServlet
public class DruidDBConfig {
    private Logger logger = LoggerFactory.getLogger(DruidDBConfig.class);

    @Value("${spring.datasource.primary.url}")
    private String dbUrl1;

    @Value("${spring.datasource.primary.username}")
    private String username1;

    @Value("${spring.datasource.primary.password}")
    private String password1;

    @Value("${spring.datasource.secondary.username}")
    private String username2;

    @Value("${spring.datasource.secondary.password}")
    private String password2;

    @Value("${spring.datasource.secondary.url}")
    private String dbUrl2;

    @Value("com.mysql.jdbc.Driver")
    private String driverClassName;

    @Value("5")
    private int initialSize;

    @Value("5")
    private int minIdle;

    @Value("20")
    private int maxActive;

    @Value("60000")
    private int maxWait;
    /**
     * 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
     */
    @Value("60000")
    private int timeBetweenEvictionRunsMillis;
    /**
     * 配置一个连接在池中最小生存的时间，单位是毫秒
     */
    @Value("300000")
    private int minEvictableIdleTimeMillis;

    @Value("SELECT 1 FROM DUAL")
    private String validationQuery;

    @Value("true")
    private boolean testWhileIdle;

    @Value("false")
    private boolean testOnBorrow;

    @Value("false")
    private boolean testOnReturn;

    /**
     * 打开PSCache，并且指定每个连接上PSCache的大小
     */
    @Value("true")
    private boolean poolPreparedStatements;

    @Value("20")
    private int maxPoolPreparedStatementPerConnectionSize;
    /**
     * 配置监控统计拦截的filters，去掉后监控界面sql无法统计，'wall'用于防火墙
     */
    @Value("stat,wall,log4j")
    private String filters;
    /**
     * 通过connectProperties属性来打开mergeSql功能；慢SQL记录
     */
    @Value("druid.stat.mergeSql=true;druid.stat.slowSqlMillis=500")
    private String connectionProperties;

    @Bean(name = "primaryDataSource")
    @Qualifier("primaryDataSource")
    public DataSource dataSource() {
        return getDruidDataSource(username1, password1, dbUrl1,"primaryDataSource");
    }

    @Bean(name = "secondaryDataSource")
    @Qualifier("secondaryDataSource")
    @Primary
    public DataSource secondaryDataSource() {
        return getDruidDataSource(username2, password2, dbUrl2,"secondaryDataSource");
    }

    private DataSource getDruidDataSource(String username, String password, String url,String dataSourceName) {
        DruidXADataSource datasource = new DruidXADataSource ();

        datasource.setUrl(url);
        datasource.setUsername(username);
        datasource.setPassword(password);
        datasource.setDriverClassName(driverClassName);

        //configuration
        datasource.setInitialSize(initialSize);
        datasource.setMinIdle(minIdle);
        datasource.setMaxActive(maxActive);
        datasource.setMaxWait(maxWait);
        datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        datasource.setValidationQuery(validationQuery);
        datasource.setTestWhileIdle(testWhileIdle);
        datasource.setTestOnBorrow(testOnBorrow);
        datasource.setTestOnReturn(testOnReturn);
        datasource.setPoolPreparedStatements(poolPreparedStatements);
        datasource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
        try {
            datasource.setFilters(filters);
        } catch (SQLException e) {
            logger.error("druid configuration initialization filter : {0}", e);
        }
        datasource.setConnectionProperties(connectionProperties);

        AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
        xaDataSource.setXaDataSource(datasource);
        xaDataSource.setUniqueResourceName(dataSourceName);
        return xaDataSource;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        hibernateJpaVendorAdapter.setShowSql(true);
        hibernateJpaVendorAdapter.setGenerateDdl(true);
        hibernateJpaVendorAdapter.setDatabase(Database.MYSQL);
        return hibernateJpaVendorAdapter;
    }

} 