package elena.app1.addressbook.tests;

import org.testng.annotations.Test;

public class GroupDeletionTests extends TestBase {



    @Test
    public void testGroupDeletion() {
        app.gotoGroupPage();
        app.selectListElement();
        app.deleteSelectedGroups();
        app.returnToGroupPage();
    }


}
