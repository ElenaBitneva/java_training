package elena.app1.addressbook.tests;

import elena.app1.addressbook.model.ContactData;
import elena.app1.addressbook.model.Contacts;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {



    @Test
    public void testContactCreation() {

        app.goTo().homePage();
        Contacts before = app.contact().all();
        app.goTo().contactPage();
        ContactData contact = new ContactData().withFirstname("Elena11").withLastname("Bitneva");
        app.contact().create(contact);
        app.goTo().homePage();
        Contacts after = app.contact().all();

        assertThat(after.size(), equalTo (before.size()+1));
        assertThat(after, equalTo(before.
                withAdded(contact.withId(after.stream().mapToInt((c) ->c.getId()).max().getAsInt()))));

    }


}
