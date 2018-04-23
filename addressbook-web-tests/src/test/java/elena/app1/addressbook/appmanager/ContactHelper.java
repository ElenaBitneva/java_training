package elena.app1.addressbook.appmanager;

import elena.app1.addressbook.model.ContactData;
import elena.app1.addressbook.model.Contacts;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by elina_000 on 17.03.2018.
 */
public class ContactHelper extends BaseHelper {


    public ContactHelper(WebDriver wd) {

        super(wd);
    }

    public void homePage() {
        click(By.linkText("home"));
    }

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
        type(By.name("address2"),contactData.getAddress2());
        type(By.name("email"),contactData.getEmail());
        type(By.name("email2"),contactData.getEmail2());
        type(By.name("email3"),contactData.getEmail3());
        type(By.name("mobile"),contactData.getMobilephone());
        type(By.name("work"),contactData.getWorkphone());
        type(By.name("home"),contactData.getHomephone());

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



    public void submitContactModification() {
        click(By.name("update"));
    }

    public void create(ContactData contact) {
        fillContactForm(contact);
        submitContactCreation();

    }
    public void modify(ContactData contact) {
        initContactModificationById(contact.getId());
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
    public void initContactModificationById(int id) {
        wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']",id))).click();
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
            String allPhones = tdElements.get(5).getText();
            String allEmails = tdElements.get(4).getText();
            String allAddresses = tdElements.get(3).getText();
            contacts.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname)
            .withAllPhones(allPhones).withAllEmails(allEmails).withAllAddresses(allAddresses));
        }
        return contacts;
    }


    public ContactData infoFromEditForm(ContactData contact) {
        initContactModificationById(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getText();
        String address2 = wd.findElement(By.name("address2")).getText();
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");

        returnToHomePage();
        return new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname)
                .withHomephone(home).withMobilephone(mobile).withWorkphone(work)
                .withEmail(email).withEmail2(email2).withEmail3(email3)
                .withAddress(address).withAddress2(address2);
    }


    public Object mergeAddresses(ContactData contact) {
        return Arrays.asList(contact.getAddress(),contact.getAddress2()).stream().filter((s) -> !s.equals(""))
                .map(ContactHelper::cleanedAddresses)
                .collect(Collectors.joining("\n"));
    }

    public String mergePhones(ContactData contact) {
        return Arrays.asList(contact.getMobilephone(), contact.getHomephone(), contact.getWorkphone())
                .stream().filter((s) -> !s.equals(""))
                .map(ContactHelper::cleanedText)
                .collect(Collectors.joining("\n"));

    }
    public String mergeEmails(ContactData contact) {
        return Arrays.asList(contact.getEmail(),contact.getEmail2(),contact.getEmail3())
                .stream().filter((s) -> !s.equals(""))
                .map(ContactHelper::cleanedText)
                .collect(Collectors.joining("\n"));


    }

    private static String cleanedText(String text) {
        return text.replaceAll("\\s", "").replaceAll("[-()]", "");
    }


    private static String cleanedAddresses(String address) {
        return address.replaceAll("[-()]", "");

    }
}
