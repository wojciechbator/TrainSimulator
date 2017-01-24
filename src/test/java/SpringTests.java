import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import trainSimulator.BootApplication;
import trainSimulator.configuration.PersistenceConfiguration;
import trainSimulator.services.UserService;

import static org.junit.Assert.assertNotEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfiguration.class, BootApplication.class})
public class SpringTests
{
    //DEPENDENCY INJECTION
    @Autowired
    @Qualifier("userService")
    UserService userService;

    @Test
    public void test_userServiceBeanIsInstantiated_shouldReturnTrue() {

    }

    @Test
    public void test_hibernateConfigurationShouldBeFulfilled() {

    }
}