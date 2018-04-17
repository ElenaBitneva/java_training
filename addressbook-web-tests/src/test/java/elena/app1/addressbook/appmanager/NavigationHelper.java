package elena.app1.addressbook.appmanager;

import elena.app1.addressbook.model.ContactData;
import elena.app1.addressbook.model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by elina_000 on 17.03.2018.
 */
public class NavigationHelper extends BaseHelper {

    public NavigationHelper(WebDriver wd) {
        super(wd);
    }

    public void groupPage() {

        click(By.linkText("groups"));
    }


    public void contactPage() {
        click(By.linkText("add new"));
    }

    public void homePage() {
        if (isElementPresent(By.id("maintable"))){
            return;
    }

    click(By.linkText("home"));
}


    public boolean isThereAnElement() {
       return isElementPresent(By.name("selected[]"));
    }

    public int getGroupCount() {
        return wd.findElements(By.name("selected[]")).size();
    }




}
