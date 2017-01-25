import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import trainSimulator.BootApplication;
import trainSimulator.configuration.PersistenceConfiguration;

import java.net.URI;

/**
 * Created by wojciech on 24.01.17.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfiguration.class, BootApplication.class})
public class SeleniumTests {

    @Autowired
    private URI URIForSelenium;
    @Autowired
    private ChromeDriver chromeDriver;

    @Test
    public void test_simpleSeleniumTest() {
        chromeDriver.get(URIForSelenium.toString());
        chromeDriver.findElementById("generatorTabPanel").click();
    }
}
