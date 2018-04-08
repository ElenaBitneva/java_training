package elena.app1.addressbook.tests;

import elena.app1.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class GroupDeletionTests extends TestBase {



    @Test
    public void testGroupDeletion() {
        app.getNavigationHelper().gotoGroupPage();

        if (! app.getNavigationHelper().isThereAnElement()){
              app.getGroupHelper().createGroup(new GroupData("test1", "test2", "test3"));
        }
        List<GroupData> before = app.getNavigationHelper().getGroupList();
        app.getNavigationHelper().selectListElement(before.size()-1);
        app.getGroupHelper().deleteSelectedGroups();
        app.getGroupHelper().returnToGroupPage();
        List<GroupData> after = app.getNavigationHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size() - 1);
        before.remove(before.size()-1);
        Assert.assertEquals(before, after);
    }



}
