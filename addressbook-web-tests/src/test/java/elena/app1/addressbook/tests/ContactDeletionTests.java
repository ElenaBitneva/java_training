package elena.app1.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase {

    
    @Test
    public void testContactDeletion() {

        app.getContactHelper().returnToHomePage();
        app.getNavigationHelper().selectListElement();
        app.getContactHelper().deleteSelectedContacts();
        app.getContactHelper().clickOKinPopup();

    }



}