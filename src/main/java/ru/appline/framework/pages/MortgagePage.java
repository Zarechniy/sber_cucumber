package ru.appline.framework.pages;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.appline.framework.managers.DriverManager;

import static ru.appline.framework.managers.DriverManager.getDriver;

/**
 * Класс описывающий страничку страхование путешественников
 */
public class MortgagePage extends BasePage {

    @FindBy(xpath = "//div[contains(@class,'kit-col_xs_12')]//h1")
    private WebElement pageTitle;

    @FindBy(xpath = "//iframe[@id='iFrameResizer0']")
    WebElement frameMortgagePage;

    @FindBy(xpath = "//label[contains(.,'недвижимости')]//input")
    private WebElement estatePriceInput;
    //label[contains(.,'недвижимости')]//input
    //input[@class='dc-input__input-6-1-2']

    @FindBy(xpath = "//label[contains(.,'Первоначальный')]//input")
    private WebElement firstPaymentInput;

    @FindBy(xpath = "//label[contains(.,'Срок')]//input")
    private WebElement termOfMortgage;

    @FindBy(xpath = "//div[@class='_2cwqL96CPji6zNQD6fZqsT']//span[contains(.,'Домклик')]/../..//input")
    private WebElement dealOnDomclickButton;

    @FindBy(xpath = "//div[@class='_2cwqL96CPji6zNQD6fZqsT']//span[contains(.,'Страхование')]/../..//input")
    private WebElement healthAndLifeInsuranceButton;

    @FindBy(xpath = "//div[@class='_2cwqL96CPji6zNQD6fZqsT']//span[contains(.,'Электронная')]/../..//input")
    private WebElement electronicDealRegisterButton;

    @FindBy(xpath = "//div[@data-test-id='main-results-block']//span[contains(.,'Ежемесячный')]/../..//span[2]/span")
    private WebElement monthlyPayment;

    @FindBy(xpath = "//div[@data-test-id='main-results-block']//span[contains(.,'кредита')]/../..//span[2]/span")
    private WebElement totalMortgagePrice;

    @FindBy(xpath = "//div[@data-test-id='main-results-block']//span[contains(.,'ставка')]/../..//span[2]/span")
    private WebElement percentOfMortgage;

    @FindBy(xpath = "//div[@data-test-id='required-income-block']//span[contains(.,'Необходимый доход')]/../..//span[2]/span")
    private WebElement neededSalary;

    @FindBy(xpath = "//h2[contains(.,'Рассчитайте ипотеку')]")
    private WebElement headerCountMortgage;

    /**
     * Проверка открытия страницы, путём проверки title страницы
     *
     * @return MortgagePage - т.е. остаемся на этой странице
     */
    public MortgagePage checkOpenMortgagePage() {
        DriverManager.getDriver().manage().deleteAllCookies();
        Assertions.assertEquals("Ипотека на вторичное жильё от 10,4%", pageTitle.getText(),
                "Заголовок отсутствует/не соответствует требуемому");
        return this;
    }

    /**
     * Метод заполнения полей
     *
     * @param nameField - имя веб элемента, поля ввода
     * @param value     - значение вводимое в поле
     * @return MortgagePage - т.е. остаемся на этой странице
     */
    public MortgagePage fillField(String nameField, String value) {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameMortgagePage));
        WebElement element = null;
        switch (nameField) {
            case "Стоимость недвижимости":
                fillInputField(estatePriceInput, value);
                element = estatePriceInput;
                break;
            case "Первоначальный взнос":
                fillInputField(firstPaymentInput, value);
                element = firstPaymentInput;
                break;
            case "Срок кредита":
                fillInputField(termOfMortgage, value);
                element = termOfMortgage;
                break;
            default:
                Assertions.fail("Поле с наименованием '" + nameField + "' отсутствует на странице " +
                        "'Ипотека на вторичное жилье'");
        }
        Assertions.assertEquals(value, element.getAttribute("value"),
                "Поле '" + nameField + "' было заполнено некорректно");
        getDriver().switchTo().defaultContent();
        return this;
    }

    public MortgagePage clickDealOnDomclickButton() {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameMortgagePage));
        js.executeScript("arguments[0].click();", dealOnDomclickButton);
        wait.until(ExpectedConditions
                .attributeToBe(dealOnDomclickButton, "aria-checked", "false"));
        getDriver().switchTo().defaultContent();
        return this;
    }

    public MortgagePage clickHealthAndLifeInsuranceButton() {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameMortgagePage));
        js.executeScript("arguments[0].click();", healthAndLifeInsuranceButton);
        wait.until(ExpectedConditions
                .attributeToBe(healthAndLifeInsuranceButton, "aria-checked", "false"));
        getDriver().switchTo().defaultContent();
        return this;
    }

    public MortgagePage clickElectronicDealRegisterButton() {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameMortgagePage));
        scrollToElementJs(electronicDealRegisterButton);
        js.executeScript("arguments[0].click();", electronicDealRegisterButton);
        wait.until(ExpectedConditions
                .attributeToBe(electronicDealRegisterButton, "aria-checked", "false"));
        scrollToElementJs(monthlyPayment);
        getDriver().switchTo().defaultContent();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this;
    }

    /**
     * Проверка соотвествия рассчетов
     *
     * @param nameField - имя веб элемента
     * @return MortgagePage - т.е. остаемся на этой странице
     */
    public MortgagePage checkOutputFields(String nameField, int value) {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameMortgagePage));
        switch (nameField) {
            case "Ежемесячный платеж":
                scrollToElementJs(monthlyPayment);
                checkIfInputAndOutputAreCorrect(monthlyPayment, value);
                break;
            case "Сумма кредита":
                scrollToElementJs(totalMortgagePrice);
                checkIfInputAndOutputAreCorrect(totalMortgagePrice, value);
                break;
            case "Необходимый доход":
                scrollToElementJs(neededSalary);
                checkIfInputAndOutputAreCorrect(neededSalary, value);
                break;
            case "Процентная ставка":
                scrollToElementJs(percentOfMortgage);
                checkIfInputAndOutputAreCorrect(percentOfMortgage, value);
                break;
            default:
                Assertions.fail("Поле с наименованием '" + nameField + "' отсутствует на странице " +
                        "'Ипотека вторичное жилье'");
        }
        getDriver().switchTo().defaultContent();
        return this;
    }
}
