package elena.app1.addressbook.tests;


import elena.app1.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;


public class
 GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() {

        app.getNavigationHelper().gotoGroupPage();
        List<GroupData> before = app.getNavigationHelper().getGroupList();
        GroupData group = new GroupData("test3", null, null);
        app.getGroupHelper().createGroup(group);
        List<GroupData> after = app.getNavigationHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size() +1);

        group.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
        before.add(group);
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
    }

}
