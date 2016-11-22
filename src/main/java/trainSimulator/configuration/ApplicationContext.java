package trainSimulator.configuration;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import trainSimulator.repositories.*;
import trainSimulator.repositories.implementations.*;

/**
 * Created by mitron-wojtek on 15.11.16.
 */
@Configuration
@ComponentScan(basePackages = "trainSimulator")
@EnableWebMvc
public class ApplicationContext extends WebMvcConfigurerAdapter {

    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    //Read statics
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Bean
    public PropertyPlaceholderConfigurer getPropertyPlaceholderConfigurer() {
        PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();
        ppc.setLocation(new ClassPathResource("database.properties"));
        ppc.setIgnoreUnresolvablePlaceholders(true);
        return ppc;
    }

    @Autowired
    @Bean(name = "eventLogDao")
    public EventLogsDao getEventLogsDao(SessionFactory sessionFactory) {
        return new EventLogsDaoImplementation(sessionFactory);
    }

    @Autowired
    @Bean(name = "generatorParametersDao")
    public GeneratorParametersDao getGeneratorParametersDao(SessionFactory sessionFactory) {
        return new GeneratorParametersDaoImplementation(sessionFactory);
    }

    @Autowired
    @Bean(name = "passengersDao")
    public PassengersDao getPassengersDao(SessionFactory sessionFactory) {
        return new PassengersDaoImplementation(sessionFactory);
    }

    @Autowired
    @Bean(name = "routesDao")
    public RoutesDao getRoutesDao(SessionFactory sessionFactory) {
        return new RoutesDaoImplementation(sessionFactory);
    }

    @Autowired
    @Bean(name = "stationsDao")
    public StationsDao getStationsDao(SessionFactory sessionFactory) {
        return new StationsDaoImplementation(sessionFactory);
    }

    @Autowired
    @Bean(name = "ticketsDao")
    public TicketsDao getTicketsDao(SessionFactory sessionFactory) {
        return new TicketsDaoImplementation(sessionFactory);
    }

    @Autowired
    @Bean(name = "timetableEntitiesDao")
    public TimetableEntitiesDao getTimetableEntitiesDao(SessionFactory sessionFactory) {
        return new TimetableEntitiesDaoImplementation(sessionFactory);
    }

    @Autowired
    @Bean(name = "trainsDao")
    public TrainsDao getTrainsDao(SessionFactory sessionFactory) {
        return new TrainsDaoImplementation(sessionFactory);
    }
}
