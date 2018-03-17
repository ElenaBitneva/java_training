package elena.app1.addressbook.tests;

import elena.app1.addressbook.tests.TestBase;
import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase {

    
    @Test
    public void testContactDeletion() {

        app.returnToHomePage();
        app.selectListElement();
        app.deleteSelectedContacts();
        app.clickOKinPopup();

    }



}