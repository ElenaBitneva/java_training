package elena.app1.addressbook.appmanager;

import elena.app1.addressbook.model.ContactData;
import elena.app1.addressbook.model.Contacts;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by elina_000 on 17.03.2018.
 */
public class ContactHelper extends BaseHelper {


    public ContactHelper(WebDriver wd) {

        super(wd);
    }

    /*public void homePage() {
        click(By.linkText("home"));
    }*/

    public void submitContactCreation() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    protected void click(By locator) {
        wd.findElement(locator).click();
    }

    public void fillContactForm(ContactData contactData) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("company"), contactData.getCompany());
        type(By.name("address"), contactData.getAddress());
        type(By.name("work"), contactData.getWorkphone());
    }

    protected void type(By locator, String text) {
        click(locator);
        wd.findElement(locator).clear();
        wd.findElement(locator).sendKeys(text);
    }

    public void clickOKinPopup() {
        wd.switchTo().alert().accept();
    }

    public void deleteSelectedContacts() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void initContactModification() {
        click(By.xpath("//img[@title='Edit']"));
    }

    public void submitContactModification() {
        click(By.name("update"));
    }

    public void create(ContactData contact) {
        fillContactForm(contact);
        submitContactCreation();

    }
    public void modify(ContactData contact) {
        initContactModification();
        fillContactForm(contact);
        submitContactModification();

    }
    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        deleteSelectedContacts();
        clickOKinPopup();
        returnToHomePage();
    }

    private void returnToHomePage() {
        if (isElementPresent(By.id("maintable"))){
            return;
        }

        click(By.linkText("home"));
    }

    private void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }
    public Contacts all() {
        Contacts contacts = new Contacts();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements){
            List<WebElement> tdElements = element.findElements(By.tagName("td"));
            String firstname = tdElements.get(2).getText();
            String lastname = tdElements.get(1).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            contacts.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname));
        }
        return contacts;
    }
}
