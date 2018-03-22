package elena.app1.addressbook.tests;

import elena.app1.addressbook.model.GroupData;
import org.testng.annotations.Test;

public class GroupDeletionTests extends TestBase {



    @Test
    public void testGroupDeletion() {
        app.getNavigationHelper().gotoGroupPage();
        if (! app.getNavigationHelper().isThereAnElement()){
              app.getGroupHelper().createGroup(new GroupData("test1", "test2", "test3"));
        }
        app.getNavigationHelper().selectListElement();
        app.getGroupHelper().deleteSelectedGroups();
        app.getGroupHelper().returnToGroupPage();
    }



}
