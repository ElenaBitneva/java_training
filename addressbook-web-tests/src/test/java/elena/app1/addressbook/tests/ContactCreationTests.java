package elena.app1.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import elena.app1.addressbook.model.ContactData;
import elena.app1.addressbook.model.Contacts;
import elena.app1.addressbook.model.GroupData;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {
    @DataProvider
    public Iterator<Object[]> validContactsFromJson() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")))) {
        String json = "";
        String line = reader.readLine();
        while (line != null) {
            json +=line;
            line = reader.readLine();
        }

        Gson gson = new Gson();
        List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>(){}.getType());
        return contacts.stream().map((g)-> new Object[] {g}).collect(Collectors.toList()).iterator();
        }
    }



    @Test(dataProvider = "validContactsFromJson")
    public void testContactCreation(ContactData contact) {

        app.goTo().homePage();
        Contacts before = app.db().contacts();
        app.goTo().contactPage();
        app.contact().create(contact);
        app.goTo().homePage();
        Contacts after = app.db().contacts();

        assertThat(after.size(), equalTo (before.size()+1));
        assertThat(after, equalTo(before.
                withAdded(contact.withId(after.stream().mapToInt((c) ->c.getId()).max().getAsInt()))));

    }


}
