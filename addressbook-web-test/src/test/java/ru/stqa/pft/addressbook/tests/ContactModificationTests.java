package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Set;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePrecondition() {
        app.goTo().homePage();
        app.сontact().createContactIfNotExist(new ContactData().withFirstname("Ostap").withLastname("Bender")
                .withAddress("221B Baker Street").withEmail("testTest@mail.ru").withGroup("Test1"));
    }

    @Test(enabled = true)
    public void testContactModification() {
        Set<ContactData> before = app.сontact().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstname("Фрай")
                .withLastname("Джей").withAddress("Маркса проспект").withMobile("+78887774433")
                .withEmail("testFR@mail.com");
        app.сontact().modify(contact);
        Set<ContactData> after = app.сontact().all();
        Assert.assertEquals(after.size(), before.size());
        
        before.remove(modifiedContact);
        before.add(contact);
        Assert.assertEquals(before, after);
    }
}