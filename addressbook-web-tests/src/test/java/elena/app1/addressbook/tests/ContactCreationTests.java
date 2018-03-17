package elena.app1.addressbook.tests;

import elena.app1.addressbook.model.ContactData;
import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {



    @Test
    public void testContactCreation() {

        app.getNavigationHelper().gotoContactPage();
        app.fillContactForm(new ContactData("Elena", "Bitneva", "Apple Inc.", "1 Infinite Loop Cupertino, CA, US 95014", "781-975-9202"));
        app.submitContactCreation();
        app.returnToHomePage();
    }


}
