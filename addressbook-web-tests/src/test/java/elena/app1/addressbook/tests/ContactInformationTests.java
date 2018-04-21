package elena.app1.addressbook.tests;

import elena.app1.addressbook.model.ContactData;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by elina_000 on 18.04.2018.
 */
public class ContactInformationTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();
        if (app.contact().all().size() == 0) {
            app.goTo().contactPage();
            app.contact().create(new ContactData().withFirstname("Elena").withLastname("Bitneva")
            .withWorkphone("7(812)428-85-30").withMobilephone("89111401086")
            .withEmail("elina_336@mail.ru").withEmail2("ea.b@gmail.com").withEmail3("eva82@gmail.com")
            .withAddress("1 Infinite Loop Cupertino, CA, US 95014").withAddress2("131 Shore Dr.Plymouth, MA,US 02036"));
            app.goTo().homePage();
        }
    }
        @Test
        public void testContactInformation() {
            app.goTo().homePage();
            ContactData contact = app.contact().all().iterator().next();
            ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

            assertThat(contact.getAllPhones(),equalTo(app.contact().mergePhones(contactInfoFromEditForm)));
            assertThat(contact.getAllAddresses(),equalTo(app.contact().mergeAddresses(contactInfoFromEditForm)));
            assertThat(contact.getAllEmails(), equalTo(app.contact().mergeEmails(contactInfoFromEditForm)));
        }
}
