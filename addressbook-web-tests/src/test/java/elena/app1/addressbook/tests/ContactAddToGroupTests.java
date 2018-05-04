package elena.app1.addressbook.tests;

import elena.app1.addressbook.model.ContactData;
import elena.app1.addressbook.model.Contacts;
import elena.app1.addressbook.model.GroupData;
import elena.app1.addressbook.model.Groups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;


/**
 * Created by elina_000 on 26.04.2018.
 */
public class ContactAddToGroupTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
    }

    @Test
    public void testContactAddToGroup() {
        app.goTo().homePage();


        Contacts contacts = app.db().contacts();
        Groups groups = app.db().groups();

        Optional<ContactData> contactCandidate;
        GroupData groupCandidate = null;

        if (contacts.size() == 0) { // если нет контактов добавляем контакт
            contactCandidate = Optional.ofNullable(new ContactData().withFirstname("Elena").withLastname("Bitneva"));

            app.goTo().contactPage();
            app.contact().create(contactCandidate.get());
            contacts = app.db().contacts();
            contactCandidate = Optional.ofNullable(contacts.iterator().next());
            app.goTo().homePage();
        } else {

            // находим контакт который не входит хотя бы в одну группу
            contactCandidate = contacts.stream().filter(
                    (c) -> groups.stream().filter(g -> !c.getGroups().contains(g)).count() > 0).findFirst();
        }

        if (groups.size() == 0 || !contactCandidate.isPresent()) {  // если нет ни одной группы или существующие контакты входят во все группы, создаем группу
            groupCandidate = new GroupData().withName("test1");

            app.goTo().groupPage();
            app.group().create(groupCandidate);
            groups.clear();
            groups.addAll(app.db().groups());
            app.goTo().homePage();
        }

        if (!contactCandidate.isPresent()) {
            contactCandidate = Optional.ofNullable(contacts.iterator().next());
        }

        ContactData c = contactCandidate.get();
        groupCandidate = groups.stream().filter(g -> !c.getGroups().contains(g)).findFirst().get();


        app.contact().selectContactById(contactCandidate.get().getId());
        app.contact().selectGroup(groupCandidate);
        app.contact().addToGroup();
    }

}
