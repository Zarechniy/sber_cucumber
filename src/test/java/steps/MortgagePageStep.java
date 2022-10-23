package steps;

import io.cucumber.java.ru.И;
import ru.appline.framework.managers.PageManager;

public class MortgagePageStep {

    PageManager pageManager = new PageManager();

    @И("^Проверить открытие страницы MortgagePage$")
    public void checkOpenMortgagePage() {
        pageManager.getMortgagePage().checkOpenMortgagePage();
    }

    @И("^Заполнить поле (.+) значение (.+)$")
    public void fillField(String nameField, String value) {
        pageManager.getMortgagePage().fillField(nameField, value);
    }

    @И("^Кликнуть по кнопке Сделка на Домклик$")
    public void clickDealOnDomclickButton() {
        pageManager.getMortgagePage().clickDealOnDomclickButton();
    }

    @И("^Кликнуть по кнопке Страхование жизни и здоровья$")
    public void clickHealthAndLifeInsuranceButton() {
        pageManager.getMortgagePage().clickHealthAndLifeInsuranceButton();
    }

    @И("^Кликнуть по кнопке Электронная регистрация$")
    public void clickElectronicDealRegisterButton() {
        pageManager.getMortgagePage().clickElectronicDealRegisterButton();
    }

    @И("^Проверить что поле (.+) соответсвует значению (\\d+)")
    public void checkOutputFields(String nameField, int value) {
    pageManager.getMortgagePage().checkOutputFields(nameField, value);
    }

}
