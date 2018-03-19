package elena.app1.addressbook.tests;

import elena.app1.addressbook.model.ContactData;
import org.testng.annotations.Test;

/**
 * Created by elina_000 on 18.03.2018.
 */
public class ContactModificationTests extends TestBase {
    @Test
    public void testContactModification() {
        app.getNavigationHelper().gotoToHomePage();
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new ContactData("Elena", "Bitneva", "Apple Inc.", "1 Infinite Loop Cupertino, CA, US 95014", "781-975-9202"));
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().gotoToHomePage();
    }
}
