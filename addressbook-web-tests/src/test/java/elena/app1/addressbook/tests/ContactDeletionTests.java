package elena.app1.addressbook.tests;

import elena.app1.addressbook.model.ContactData;
import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase {

    
    @Test
    public void testContactDeletion() {

        app.getNavigationHelper().gotoToHomePage();
        if (! app.getNavigationHelper().isThereAnElement()){
              app.getNavigationHelper().gotoContactPage();
              app.getContactHelper().createContact(new ContactData("Elena", "Bitneva", "Apple Inc.",
                      "1 Infinite Loop Cupertino, CA, US 95014", "781-975-9202"));
              app.getNavigationHelper().gotoToHomePage();
        }
        app.getNavigationHelper().selectListElement();
        app.getContactHelper().deleteSelectedContacts();
        app.getContactHelper().clickOKinPopup();

    }



}