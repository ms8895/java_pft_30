package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() {
        app.getNavigationHelper().gotoHomePage();
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createGroupAndContact();
        }
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactFormIsGroup(new ContactData("Фрай", "Джей", null,
                "+78887774433", "testFR@mail.com", null), false);
        app.getContactHelper().submitContactModification();
        app.getContactHelper().returnContactHomePage();
    }
}