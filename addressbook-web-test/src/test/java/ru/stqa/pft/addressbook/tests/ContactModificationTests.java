package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

    @Test(enabled = true)
    public void testContactModification() {
        app.goTo().gotoHomePage();
        app.getContactHelper().createContactIfNotExist(new ContactData("Ostap", "Bender",
                "221B Baker Street", null, "testTest@mail.ru", "Test1"));
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectContact(before.size() - 1);
        app.getContactHelper().initLastContactModification();
        ContactData contact = new ContactData(before.get(before.size() - 1).getId(), "Фрай", "Джей", "Маркса проспект",
                "+78887774433", "testFR@mail.com", null);
        app.getContactHelper().fillContactFormIsGroup(contact);
        app.getContactHelper().submitContactModification();
        app.getContactHelper().returnContactHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size() - 1);
        before.add(contact);
        Comparator<? super ContactData> byFirstname = (c1, c2) -> CharSequence.compare(c1.getFirstname(), c2.getFirstname());
        before.sort(byFirstname);
        after.sort(byFirstname);
        Assert.assertEquals(before, after);

    }
}