package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePrecondition() {
        app.goTo().homePage();
        app.сontact().createContactIfNotExist(new ContactData().withFirstname("Ostap").withLastname("Bender")
                .withAddress("221B Baker Street").withEmail("testTest@mail.ru").withGroup("Test1"));
    }

    @Test(enabled = true)
    public void testContactModification() {
        List<ContactData> before = app.сontact().list();
        int index = before.size() - 1;
        ContactData contact = new ContactData().withId(before.get(index).getId()).withFirstname("Фрай")
                .withLastname("Джей").withAddress("Маркса проспект").withMobile("+78887774433")
                .withEmail("testFR@mail.com");

        app.сontact().modify(index, contact);
        List<ContactData> after = app.сontact().list();
        Assert.assertEquals(after.size(), before.size());

        before.remove(index);
        before.add(contact);
        Comparator<? super ContactData> byFirstname = (c1, c2) -> CharSequence.compare(c1.getFirstname(), c2.getFirstname());
        before.sort(byFirstname);
        after.sort(byFirstname);
        Assert.assertEquals(before, after);
    }
}