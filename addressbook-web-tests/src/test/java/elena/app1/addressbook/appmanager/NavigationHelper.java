package elena.app1.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by elina_000 on 17.03.2018.
 */
public class NavigationHelper extends BaseHelper {

    public NavigationHelper(WebDriver wd) {
        super(wd);
    }

    public void gotoGroupPage() {
        click(By.linkText("groups"));
    }

    public void selectListElement() {
        click(By.name("selected[]"));
    }

    public void gotoContactPage() {
        click(By.linkText("add new"));
    }
    public void gotoToHomePage() {
        click(By.linkText("home"));
    }}
