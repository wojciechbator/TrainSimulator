package configuration;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/**
 * Created by mitron-wojtek on 19.11.16.
 */
@Configuration
@ComponentScan(basePackages = "repository")
@PropertySource("classpath:database.properties")
public class PostgresConfiguration {
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

    @Bean(name = "dataSource")
    public DataSource dataSource() {
        DriverManagerDataSource driver = new DriverManagerDataSource();
        driver.setDriverClassName("org.postgresql.Driver");
        driver.setUrl("jdbc:" + dbms + "://" + serverName + ":" + port + ":" + dbName);
        driver.setUsername(user);
        driver.setPassword(dbPass);
        return driver;
    }

    @Bean
    public EntityManagerFactory entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setPackagesToScan("models");
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setPersistenceProvider(new HibernatePersistenceProvider());
        return (EntityManagerFactory) entityManagerFactoryBean;
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
