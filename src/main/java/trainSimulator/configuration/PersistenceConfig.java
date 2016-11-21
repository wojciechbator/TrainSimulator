package trainSimulator.configuration;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

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
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);
        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setJpaVendorAdapter(vendorAdapter);
        entityManagerFactory.setPackagesToScan("trainSimulator/repositories");
        entityManagerFactory.setDataSource(dataSource());
        entityManagerFactory.afterPropertiesSet();
        return entityManagerFactory.getObject();
    }

    @Autowired
    @Bean(name = "transactionManager")
    public HibernateTransactionManager getTransactionManager(
            SessionFactory sessionFactory) {

        return new HibernateTransactionManager(sessionFactory);
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Autowired
    @Bean(name = "sessionFactory")
    public SessionFactory getSessionFactory(DataSource dataSource) {
        LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
//        sessionBuilder.addAnnotatedClasses(EventLog.class, GeneratorParameter.class, Passenger.class, Route.class,
//                Station.class, Ticket.class, TimetableEntity.class, Train.class);
        sessionBuilder.scanPackages("trainSimulator/models");
        sessionBuilder.setProperty("hibernate.show_sql", "true");
        return sessionBuilder.buildSessionFactory();
    }
}
