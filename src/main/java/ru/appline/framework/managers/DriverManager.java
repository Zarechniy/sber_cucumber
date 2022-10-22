package ru.appline.framework.managers;

import org.apache.commons.exec.OS;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import ru.appline.framework.utils.PropConst;

import java.net.MalformedURLException;
import java.net.URI;

import static ru.appline.framework.utils.PropConst.*;

/**
 * Класс для управления веб драйвером
 */
public class DriverManager {

    /**
     * Переменна для хранения объекта веб-драйвера
     *
     * @see WebDriver
     */
    private static WebDriver driver;


    /**
     * Переменна для хранения объекта DriverManager
     */
    private static DriverManager INSTANCE = null;


    /**
     * Менеджер properties
     *
     * @see TestPropManager#getTestPropManager()
     */
    private final TestPropManager props = TestPropManager.getTestPropManager();
    private String browserName = props.getProperty(TYPE_BROWSER);


    /**
     * Конструктор специально был объявлен как private (singleton паттерн)
     *
     * @see DriverManager#getDriver()
     * @see DriverManager#initDriver()
     */
    private DriverManager() {
        initDriver();
    }


    /**
     * Метод ленивой инициализации веб драйвера
     *
     * @return WebDriver - возвращает веб драйвер
     */
    public static WebDriver getDriver() {
        if (INSTANCE == null) {
            INSTANCE = new DriverManager();
        }
        return driver;
    }


    /**
     * Метод для закрытия сессии драйвера и браузера
     *
     * @see WebDriver#quit()
     */
    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }


    /**
     * Метод инициализирующий веб драйвер
     */
    private void initDriver() {
        if (OS.isFamilyWindows()) {
            initDriverWindowsOsFamily();
        } else if (OS.isFamilyMac()) {
            initDriverMacOsFamily();
        } else if (OS.isFamilyUnix()) {
            initDriverUnixOsFamily();
        }
    }

    /**
     * Метод инициализирующий веб драйвер под ОС семейства Windows
     */
    private void initDriverWindowsOsFamily() {
        initDriverAnyOsFamily(PATH_GECKO_DRIVER_WINDOWS, PATH_CHROME_DRIVER_WINDOWS);
    }


    /**
     * Метод инициализирующий веб драйвер под ОС семейства Mac
     */
    private void initDriverMacOsFamily() {
        initDriverAnyOsFamily(PATH_GECKO_DRIVER_MAC, PATH_CHROME_DRIVER_MAC);
    }

    /**
     * Метод инициализирующий веб драйвер под ОС семейства Unix
     */
    private void initDriverUnixOsFamily() {
        initDriverAnyOsFamily(PATH_GECKO_DRIVER_UNIX, PATH_CHROME_DRIVER_UNIX);
    }


    /**
     * Метод инициализирующий веб драйвер под любую ОС
     *
     * @param gecko - переменная firefox из файла application.properties в классе {@link ru.appline.framework.utils.PropConst}
     * @param chrome - переменная chrome из файла application.properties в классе {@link ru.appline.framework.utils.PropConst}
     */
    private void initDriverAnyOsFamily(String gecko, String chrome) {
            switch (props.getProperty(TYPE_BROWSER)) {
                case "firefox":
                    System.setProperty("webdriver.gecko.driver", props.getProperty(gecko));
                    driver = new FirefoxDriver();
                    break;
                case "chrome":
                    System.setProperty("webdriver.chrome.driver", props.getProperty(chrome));
                    driver = new ChromeDriver();
                    break;
                case "remote":
                    DesiredCapabilities capabilities = new DesiredCapabilities();
                    capabilities.setBrowserName("chrome");
                    capabilities.setVersion("84.0");
                    capabilities.setCapability("enableVNC", true);
                    capabilities.setCapability("enableVideo", false);
                    try {
                        driver = new RemoteWebDriver(
                                URI.create("http://130.193.49.85:4444/wd/hub").toURL(),
                                capabilities
                        );
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    Assertions.fail("Типа браузера '" + props.getProperty(TYPE_BROWSER) + "' не существует во фреймворке");
            }
    }


}
