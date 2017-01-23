import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.Assert.assertNotEquals;

public class SpringTests
{
    private AnnotationConfigApplicationContext ctx = null;

    @Before
    public void contextLoad() {
        ctx = new AnnotationConfigApplicationContext();
        ctx.scan("trainSimulator");//This will load the configured components UserService, UserRepository,
        ctx.refresh();
    }

    @Test
    public void test_springContextShouldBeLoaded() {
        assertNotEquals(null, ctx);
    }



    @Test
    public void test_hibernateConfigurationShouldBeFulfilled() {

    }

    @After
    public void contextShutdown() {
        //Shutting down the spring context, as it is no longer necessary
        ctx.close();
    }

}