package ru.stqa.pft.addressbook.test;

import org.testng.annotations.Test;

public class GroupDeletionTest extends TestBase {


    @Test
    public void testGroupDeletion() throws Exception {
        app.gotoGroupPage();
        app.selectGroup();
        app.deleteSelectedGroup();
        app.returnGroupPage();
    }

}
