package elena.app1.addressbook.tests;


import com.thoughtworks.xstream.XStream;
import elena.app1.addressbook.model.GroupData;
import elena.app1.addressbook.model.Groups;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import javax.sql.rowset.spi.SyncFactory;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class
 GroupCreationTests extends TestBase {

    @DataProvider
    public Iterator<Object[]>validGroups() throws IOException {
        List<Object[]> list = new ArrayList<Object[]>();
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.xml")))) {
            String xml = "";
            String line = reader.readLine();
            while (line != null) {
                xml += line;
                line = reader.readLine();
            }
            XStream xStream = new XStream();
            xStream.processAnnotations(GroupData.class);
            List<GroupData> groups = (List<GroupData>) xStream.fromXML(xml);
            return groups.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
        }
    }

    @Test (dataProvider = "validGroups")
    public void testGroupCreation(GroupData group) {
        app.goTo().groupPage();
        Groups before = app.db().groups();
        app.group().create(group);
        Groups after = app.db().groups();
        assertThat(after.size(), equalTo( before.size() +1));
        assertThat(after, equalTo(
                before.withAdded (group.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt()))));
    }

}
