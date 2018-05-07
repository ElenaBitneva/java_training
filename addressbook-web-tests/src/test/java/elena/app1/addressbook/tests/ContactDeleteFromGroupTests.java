package elena.app1.addressbook.tests;

import elena.app1.addressbook.model.ContactData;
import elena.app1.addressbook.model.Contacts;
import elena.app1.addressbook.model.GroupData;
import elena.app1.addressbook.model.Groups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.security.cert.PKIXRevocationChecker;
import java.util.Optional;

/**
 * Created by elina_000 on 06.05.2018.
 */
public class ContactDeleteFromGroupTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
    }


    @Test
    public void testContactDeleteFromGroup() {
        app.goTo().homePage();
        Contacts contacts = app.db().contacts();
        Groups groups = app.db().groups();

        Optional<ContactData> contactCandidate;
        GroupData groupCandidate = null;

        contactCandidate = contacts.stream().filter(
                (c) -> groups.stream().filter(g -> c.getGroups().contains(g)).count() > 0).findFirst();

        if  (contactCandidate.isPresent()) { // нашли контакт назначенный на группу
            groupCandidate = contactCandidate.get().getGroups().iterator().next();
        } else {

            if (contacts.size() == 0) { // если нет контактов добавляем контакт
                app.goTo().contactPage();
                app.contact().create(new ContactData().withFirstname("Elena").withLastname("Bitneva"));

            }

            if (groups.size() == 0) {  // если нет ни одной группы, создаем группу
                app.goTo().groupPage();
                app.group().create(new GroupData().withName("test1"));
            }

            contacts = app.db().contacts();
            contactCandidate = Optional.ofNullable(contacts.iterator().next());
            app.goTo().homePage();

            groups.clear();
            groups.addAll(app.db().groups());
            app.goTo().homePage();
            groupCandidate = groups.iterator().next();

            ContactData c = contactCandidate.get();
            app.contact().selectContactById(contactCandidate.get().getId());
            app.contact().selectGroup(groupCandidate);
            app.contact().addToGroup();
        }

        app.goTo().homePage();
        app.contact().selectFromGroup(groupCandidate);
        app.contact().selectContactById(contactCandidate.get().getId());
        app.contact().removeFromGroup(groupCandidate);

      }
}
