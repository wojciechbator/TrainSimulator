import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
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

import java.util.Arrays;

/**
 * Created by wojciech on 24.01.17.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(BootApplication.class)
@WebIntegrationTest(value = "server.port=8181")
public class SeleniumTests {
    @Autowired
    private WebApplicationContext context;
    @Autowired
    private WebDriver webDriver;
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
    public void test_GoToAdminPanel() throws InterruptedException {
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("excludeSwitches", Arrays.asList("ignore-certificate-errors"));
        webDriver.get("localhost:8181");
        Thread.sleep(2000);
        WebElement generatorTabPanel = webDriver.findElement(By.id("generatorTabPanel"));
        generatorTabPanel.click();
        Thread.sleep(3000);
    }

    @Test
    public void test_GoToAdminPanelThenGenerateTimetableAndGoToTimetable() throws InterruptedException {
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("excludeSwitches", Arrays.asList("ignore-certificate-errors"));
        webDriver.get("localhost:8181");
        Thread.sleep(2000);
        webDriver.findElement(By.id("generatorTabPanel")).click();
        Thread.sleep(3000);
        WebElement generatorButton = webDriver.findElement(By.id("generateButton"));
        generatorButton.click();
        Thread.sleep(3000);
        webDriver.findElement(By.id("timetableTabPanel")).click();
        Thread.sleep(3000);
    }
}
