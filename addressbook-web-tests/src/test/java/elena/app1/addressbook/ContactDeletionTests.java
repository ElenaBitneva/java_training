package elena.app1.addressbook;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase {

    
    @Test
    public void testContactDeletion() {

        returnToHomePage();
        selectListElement();
        deleteSelectedContacts();
        clickOKinPopup();

    }



}