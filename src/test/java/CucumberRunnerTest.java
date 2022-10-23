import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"io.qameta.allure.cucumber5jvm.AllureCucumber5Jvm","progress",
                "summary"},
        glue = {"steps"},
        features = {"src/test/resources/scenario"},
        tags = {"@firstTest"}
)
public class CucumberRunnerTest {
}

//"ru.appline.framework.runner.MyAllureListener",
