package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.HashSet;
import java.util.List;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() {
        app.getNavigationHelper().gotoHomePage();
        if (!app.getContactHelper().isThereAContact()) {
            app.getGroupHelper().createNewGroup();
            app.getContactHelper().createContact(new ContactData("Ostap", "Bender",
                    "221B Baker Street", null, "testTest@mail.ru", "Test1"));
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectContact(before.size() - 1);
        app.getContactHelper().initContactModification();
        ContactData contact = new ContactData(before.get(before.size() - 1).getId(), "Фрай", "Джей", null,
                "+78887774433", "testFR@mail.com", null);
        app.getContactHelper().fillContactFormIsGroup(contact, false);
        app.getContactHelper().submitContactModification();
        app.getContactHelper().returnContactHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size() - 1);
        before.add(contact);
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(before));

    }
}