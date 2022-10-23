package steps;

import io.cucumber.java.ru.И;
import ru.appline.framework.managers.PageManager;

public class StartPageStep {

    PageManager pageManager = new PageManager();

    @И("^Перейти в главное меню (.+)$")
    public void selectBaseMenu(String nameBaseMenu) {
        pageManager.getStartPage().selectBaseMenu(nameBaseMenu);
    }

    @И("^Выбрать подменю (.+)$")
    public void selectSubMenu(String nameSubMenu) {
        pageManager.getStartPage().selectSubMenu(nameSubMenu);
    }
}
