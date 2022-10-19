package ru.appline.framework.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import ru.appline.framework.managers.InitManager;
import ru.appline.framework.managers.TestPropManager;

import static ru.appline.framework.managers.DriverManager.getDriver;
import static ru.appline.framework.utils.PropConst.APP_URL;

public class Hooks {

    @Before
    public void before() {
        InitManager.initFramework();
        getDriver().get(TestPropManager.getTestPropManager().getProperty(APP_URL));
    }

    @After
    public void after() {
        InitManager.quitFramework();
    }
}
