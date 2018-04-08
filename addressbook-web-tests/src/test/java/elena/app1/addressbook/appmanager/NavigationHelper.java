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

    public void gotoGroupPage() {

        click(By.linkText("groups"));
    }

    public void selectListElement(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public void gotoContactPage() {
        click(By.linkText("add new"));
    }

    public void gotoToHomePage() {
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

    public List<GroupData> getGroupList() {
        List<GroupData> groups = new ArrayList<GroupData>();
        List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
        for (WebElement element : elements){
            String name = element.getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            GroupData group = new GroupData(id, name, null, null);
            groups.add(group);
        }
        return groups;
    }

    public List<ContactData> getContactList() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements){
            List<WebElement> tdElements = element.findElements(By.tagName("td"));
            String firstname = tdElements.get(2).getText();
            String lastname = tdElements.get(1).getText();
            ContactData contact = new ContactData(firstname, lastname, null, null, null);
            contacts.add(contact);
        }
        return contacts;
    }
}
