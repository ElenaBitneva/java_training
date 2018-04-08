package elena.app1.addressbook.tests;

import elena.app1.addressbook.model.ContactData;
import elena.app1.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class ContactDeletionTests extends TestBase {

    
    @Test
    public void testContactDeletion() throws InterruptedException {

        app.getNavigationHelper().gotoToHomePage();
        if (! app.getNavigationHelper().isThereAnElement()){
              app.getNavigationHelper().gotoContactPage();
              app.getContactHelper().createContact(new ContactData("Elena", "Bitneva", "Apple Inc.",
                      "1 Infinite Loop Cupertino, CA, US 95014", "781-975-9202"));
              app.getNavigationHelper().gotoToHomePage();
        }
        List<ContactData> before = app.getNavigationHelper().getContactList();
        app.getNavigationHelper().selectListElement(before.size()-1);
        app.getContactHelper().deleteSelectedContacts();
        app.getContactHelper().clickOKinPopup();
        app.getNavigationHelper().gotoToHomePage();
        List<ContactData> after = app.getNavigationHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() - 1);
        before.remove(before.size()-1);
        Assert.assertEquals(before, after);

    }



}