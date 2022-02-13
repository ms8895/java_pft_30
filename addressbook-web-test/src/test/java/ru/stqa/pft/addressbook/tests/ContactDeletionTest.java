package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

public class ContactDeletionTest extends TestBase {


    @Test
    public void testContactDeletion() {
        app.getNavigationHelper().gotoHomePage();
        if (!app.getContactHelper().isThereAContact()) {
            app.getNavigationHelper().gotoGroupPage();
            if (!app.getGroupHelper().isGroupPresent("Test1")) {
                app.getGroupHelper().createGroup(new GroupData("Test1", "Test2", "Test"));
            }
            app.getNavigationHelper().gotoContactPage();
            app.getContactHelper().createContact(new ContactData("Ostap", "Bender",
                    "221B Baker Street", null, "testTest@mail.ru", "Test1"));
        }
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteSelectedContact();
        app.getContactHelper().AcceptAlertContact();
    }
}
