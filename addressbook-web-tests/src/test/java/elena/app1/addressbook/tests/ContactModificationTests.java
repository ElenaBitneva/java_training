package elena.app1.addressbook.tests;

import elena.app1.addressbook.model.ContactData;
import elena.app1.addressbook.model.Contacts;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

/**
 * Created by elina_000 on 18.03.2018.
 */
public class ContactModificationTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().homePage();
        if (app.db().contacts().size()==0) {
            app.goTo().contactPage();
            app.contact().create(new ContactData().withFirstname("Elena").withLastname("Bitneva"));
            app.goTo().homePage();
        }

    }
    @Test
    public void testContactModification() {
        Contacts before = app.db().contacts();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData().
                withId(modifiedContact.getId()).withFirstname("Elena12").withLastname("Bitneva").
                withCompany("Apple Inc.").withAddress("1 Infinite Loop Cupertino, CA, US 95014").
                withWorkphone("781-975-9202");
        app.goTo().homePage();
        app.contact().modify(contact);
        app.goTo().homePage();
        Contacts after = app.db().contacts();
        assertEquals(after.size(), before.size());
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    }


}
