package elena.app1.addressbook.tests;

import org.testng.annotations.Test;

public class GroupDeletionTests extends TestBase {



    @Test
    public void testGroupDeletion() {
        app.getNavigationHelper().gotoGroupPage();
        app.getNavigationHelper().selectListElement();
        app.getGroupHelper().deleteSelectedGroups();
        app.getGroupHelper().returnToGroupPage();
    }


}
