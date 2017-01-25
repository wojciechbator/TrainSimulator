import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import trainSimulator.BootApplication;
import trainSimulator.annotations.SeleniumTest;

import java.net.URI;

/**
 * Created by wojciech on 24.01.17.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(BootApplication.class)
@WebIntegrationTest(value = "server.port=8181")
@SeleniumTest(driver = ChromeDriver.class, baseUrl = "http://localhost:8181")
public class SeleniumTests {
    @Autowired
    private WebApplicationContext context;
    @Autowired
    private URI URIForSelenium;
    @Autowired
    private ChromeDriver chromeDriver;
    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void test_verifiesHomePageLoads() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void test_simpleSeleniumTest() throws InterruptedException {
        chromeDriver.get(URIForSelenium.toString());
        Thread.sleep(2000);
        WebElement generatorTabPanel = chromeDriver.findElementById("generatorTabPanel");
        generatorTabPanel.click();
        Thread.sleep(3000);
    }
}
