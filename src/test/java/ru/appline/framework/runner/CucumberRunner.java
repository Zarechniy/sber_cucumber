package ru.appline.framework.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/scenario",
        glue = "ru.appline.framework.steps",
        tags = "@all",
        plugin = "ru.appline.framework.utils.MyAllureListener"
)
public class CucumberRunner {
}
