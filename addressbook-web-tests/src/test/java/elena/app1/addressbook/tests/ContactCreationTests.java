package elena.app1.addressbook.tests;

import elena.app1.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {



    @Test
    public void testContactCreation() {

        app.getNavigationHelper().gotoToHomePage();
        List<ContactData> before = app.getNavigationHelper().getContactList();
        app.getNavigationHelper().gotoContactPage();
        ContactData contact = new ContactData("Elena", "Bitneva", "Apple Inc.", "1 Infinite Loop Cupertino, CA, US 95014", "781-975-9202");
        app.getContactHelper().createContact(contact);
        app.getNavigationHelper().gotoToHomePage();
        List<ContactData> after = app.getNavigationHelper().getContactList();
        Assert.assertEquals(after.size(), before.size()+1);
        before.add(contact);
        Comparator<? super ContactData> byId = (g1, g2)->Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before,after);
    }


}
