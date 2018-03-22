package elena.app1.addressbook.tests;


import elena.app1.addressbook.model.GroupData;
import org.testng.annotations.Test;




public class
 GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() {

        app.getNavigationHelper().gotoGroupPage();
        app.getGroupHelper().createGroup(new GroupData("test1", "test2", "test3"));

    }

}
