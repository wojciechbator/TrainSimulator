package trainSimulator;

import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

/**
 * Created by mitron-wojtek on 22.11.16.
 */
@SpringBootApplication
@EnableWebSecurity
@EnableAsync
public class BootApplication extends SpringBootServletInitializer {
    private static final String PATH = "/errors";

    public static void main(String[] args) {
        SpringApplication.run(BootApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(BootApplication.class);
    }

    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer() {
        return (container -> {
            //przekieruj wszystkiego rodzaju errory na URL /errors
            final ErrorPage errorPage = new ErrorPage(PATH);
            container.addErrorPages(errorPage);
        });
    }

    @Bean(destroyMethod = "quit")
    public ChromeDriver getChromeDriver() {
        return new ChromeDriver();
    }
}
