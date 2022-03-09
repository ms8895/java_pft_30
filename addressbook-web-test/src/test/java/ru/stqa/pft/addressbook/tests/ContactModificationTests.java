package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePrecondition() {
        app.goTo().homePage();
        app.сontact().createContactIfNotExist(new ContactData().withFirstname("Ostap").withLastname("Bender")
                .withAddress("221B Baker Street").withEmail("testTest@mail.ru").withGroup("Test1"));
    }

    @Test(enabled = true)
    public void testContactModification() {
        Contacts before = app.сontact().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstname("Фрай")
                .withLastname("Джей").withAddress("Маркса проспект").withMobilePhone("+78887774433")
                .withEmail("testFR@mail.com");
        app.сontact().modify(contact);
        assertThat(app.сontact().count(), equalTo(before.size()));
        Contacts after = app.сontact().all();
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    }
}