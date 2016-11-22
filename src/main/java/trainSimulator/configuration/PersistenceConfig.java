package trainSimulator.configuration;

import org.hibernate.SessionFactory;
import org.postgresql.Driver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by mitron-wojtek on 19.11.16.
 */
@Configuration
@EnableJpaRepositories("trainSimulator.repositories")
@EnableTransactionManagement
@PropertySource("classpath:database.properties")
public class PersistenceConfig {
    @Value("${serverName}")
    private String serverName;
    @Value("${dbName}")
    private String dbName;
    @Value("${dbms}")
    private String dbms;
    @Value("${port}")
    private String port;
    @Value("${dbUser}")
    private String user;
    @Value("${dbPass}")
    private String dbPass;

    @Value("${hibernate.dialect}")
    private String hibernateDialect;
    @Value("${hibernate.show_sql}")
    private String hibernateShowSql;
    @Value("${hibernate.hbm2ddl.auto}")
    private String hibernateHbm2ddlAuto;
    @Value("${hibernate.generate_statistics}")
    private String hibernateGenerateStatistics;

    @Bean(name = "dataSource")
    public DataSource dataSource() {
        DriverManagerDataSource driver = new DriverManagerDataSource();
        driver.setDriverClassName(Driver.class.getName());
        driver.setUrl("jdbc:" + dbms + "://" + serverName + ":" + port + ":" + dbName);
        driver.setUsername(user);
        driver.setPassword(dbPass);
        return driver;
    }

    @Bean
    public Properties getHibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", hibernateDialect);
        properties.put("hibernate.show_sql", hibernateShowSql);
        properties.put("hibernate.hbm2ddl.auto", hibernateHbm2ddlAuto);

        return properties;
    }

    @Bean(name = "sessionFactory")
    public SessionFactory getSessionFactory() throws Exception {
        LocalSessionFactoryBean factory = new LocalSessionFactoryBean();
        factory.setPackagesToScan("trainSimulator.models");
        factory.setDataSource(dataSource());
        factory.setHibernateProperties(getHibernateProperties());
        factory.afterPropertiesSet();
        return factory.getObject();
    }

    @Bean(name = "transactionManager")
    public HibernateTransactionManager getTransactionManager() throws Exception {
        return new HibernateTransactionManager(getSessionFactory());
    }
}
