package elena.app1.addressbook.tests;

import elena.app1.addressbook.model.ContactData;
import elena.app1.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

/**
 * Created by elina_000 on 18.03.2018.
 */
public class ContactModificationTests extends TestBase {
    @Test
    public void testContactModification() {
        app.getNavigationHelper().gotoToHomePage();
        if (! app.getNavigationHelper().isThereAnElement()) {
              app.getNavigationHelper().gotoContactPage();
              app.getContactHelper().createContact(new ContactData("Elena", "Bitneva", null, null, null));
              app.getNavigationHelper().gotoToHomePage();
        }
        List<ContactData> before = app.getNavigationHelper().getContactList();
        app.getContactHelper().initContactModification();
        ContactData contact = new ContactData(before.get(0).getId(),"Elena", "Bitneva", "Apple Inc.", "1 Infinite Loop Cupertino, CA, US 95014", "781-975-9202");
        app.getContactHelper().fillContactForm(contact);
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().gotoToHomePage();
        List<ContactData> after = app.getNavigationHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());
        before.remove(0);
        before.add(contact);
        Comparator<? super ContactData> byId = (g1, g2)->Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before,after);
    }
}
