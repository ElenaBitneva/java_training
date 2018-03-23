package elena.app1.addressbook.tests;

import elena.app1.addressbook.model.GroupData;
import org.testng.annotations.Test;

/**
 * Created by elina_000 on 18.03.2018.
 */
public class GroupModificationTests extends TestBase {

    @Test
    public void testGroupModification() {
        app.getNavigationHelper().gotoGroupPage();
        if (! app.getNavigationHelper().isThereAnElement()){
              app.getGroupHelper().createGroup(new GroupData("test1", "test2", "test3"));
        }
        app.getNavigationHelper().selectListElement();
        app.getGroupHelper().intGroupModification();
        app.getGroupHelper().fillGroupForm(new GroupData("test1", "test2", "test3"));
        app.getGroupHelper().submitGroupModification();
        app.getGroupHelper().returnToGroupPage();
    }
}
