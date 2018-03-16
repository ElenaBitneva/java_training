package elena.app1.addressbook;

import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {



    @Test
    public void testContactCreation() {

        gotoContactPage();
        fillContactForm(new ContactData("Elena", "Bitneva", "Apple Inc.", "1 Infinite Loop Cupertino, CA, US 95014", "781-975-9202"));
        submitContactCreation();
        returnToHomePage();
    }


}
