package elena.app1.addressbook.tests;

import elena.app1.addressbook.model.ContactData;
import elena.app1.addressbook.model.Contacts;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactDeletionTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().homePage();
        if ( app.db().contacts().size()==0){
            app.goTo().contactPage();
            app.contact().create(new ContactData().withFirstname("Elena13").withLastname("Bitneva"));
            app.goTo().homePage();
        }

    }
    
    @Test
    public void testContactDeletion() throws InterruptedException {
        Contacts before = app.db().contacts();
        ContactData deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        Contacts after = app.db().contacts();
        assertEquals(after.size(), before.size() - 1);
        assertThat(after, equalTo(before.without(deletedContact)));
    }

}