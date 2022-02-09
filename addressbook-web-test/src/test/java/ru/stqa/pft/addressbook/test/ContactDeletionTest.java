package ru.stqa.pft.addressbook.test;

import org.testng.annotations.Test;

public class ContactDeletionTest extends TestBase {


    @Test
    public void testContactDeletion() {
        app.getNavigationHelper().gotoHomePage();

        app.getContactHelper().selectContact();
        app.getContactHelper().deleteSelectedContact();
        app.getContactHelper().AcceptAlertContact();

    }
}
