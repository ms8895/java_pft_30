package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() {
        app.getNavigationHelper().gotoHomePage();
        if (!app.getContactHelper().isThereAContact()) {
            app.getNavigationHelper().gotoContactPage();
            app.getContactHelper().createContact(new ContactData("Ostap", "Bender",
                    "221B Baker Street", null, "testTest@mail.ru", "Test1"));
        }
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new ContactData("Фрай", "Джей", null,
                "+78887774433", "testFR@mail.com", null), false);
        app.getContactHelper().submitContactModification();
        app.getContactHelper().returnContactHomePage();
    }
}
