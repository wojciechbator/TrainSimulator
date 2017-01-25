package trainSimulator.annotations;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.BeanUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.TestExecutionListener;

import java.io.File;

import static org.springframework.core.annotation.AnnotationUtils.findAnnotation;

/**
 * Created by wojciech on 26.01.17.
 */
public class SeleniumTestExecutionListener implements TestExecutionListener {
    private WebDriver webDriver;

    @Override
    public void beforeTestClass(TestContext testContext) throws Exception {

    }

    @Override
    public void prepareTestInstance(TestContext testContext) throws Exception {
        ApplicationContext context = testContext.getApplicationContext();
        if (context instanceof ConfigurableApplicationContext) {

            SeleniumTest annotation = findAnnotation(
                    testContext.getTestClass(), SeleniumTest.class);
            webDriver = BeanUtils.instantiate(annotation.driver());

            // register the bean with bean factory

        }
    }
    @Override
    public void beforeTestMethod(TestContext testContext) throws Exception {
        SeleniumTest annotation = findAnnotation(
                testContext.getTestClass(), SeleniumTest.class);
        webDriver.get(annotation.baseUrl());

    }

    @Override
    public void afterTestMethod(TestContext testContext) throws Exception {
        if (testContext.getTestException() == null) {
            return;
        }

        File screenshot = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);

        // do stuff with the screenshot

    }

    @Override
    public void afterTestClass(TestContext testContext) throws Exception {
        if (webDriver != null) {
            webDriver.quit();
        }
    }
}
