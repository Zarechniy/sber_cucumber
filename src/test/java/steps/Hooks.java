package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import ru.appline.framework.managers.InitManager;
import ru.appline.framework.managers.TestPropManager;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static ru.appline.framework.managers.DriverManager.getDriver;
import static ru.appline.framework.utils.PropConst.APP_URL;

public class Hooks {

    @Before
    public void before() {
        InitManager.initFramework();
        getDriver().get(TestPropManager.getTestPropManager().getProperty(APP_URL));
    }

    @After
    public void afterEach(Scenario scenario) {
        if (scenario.isFailed()) {
            Allure.addAttachment(
                    "failureScreenshot",
                    "image/png",
                    addScreenshot(),
                    "png"
            );
        }
        InitManager.quitFramework();
    }

    private static InputStream addScreenshot() {
        byte[] screenshot = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
        return new ByteArrayInputStream(screenshot);
    }

}
