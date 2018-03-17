package elena.app1.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by elina_000 on 17.03.2018.
 */
public class NavigationHelper {
    private FirefoxDriver wd;

    public NavigationHelper(FirefoxDriver wd) {
        this.wd = wd;
    }

    public void gotoGroupPage() {
        wd.findElement(By.linkText("groups")).click();
    }

    public void selectListElement() {
        wd.findElement(By.name("selected[]")).click();
    }

    public void gotoContactPage() {
        wd.findElement(By.linkText("add new")).click();
    }
}
