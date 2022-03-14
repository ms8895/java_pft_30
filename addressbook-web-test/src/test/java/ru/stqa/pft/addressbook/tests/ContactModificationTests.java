package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePrecondition() {
        app.goTo().homePage();
        app.сontact().createContactIfNotExist(new ContactData().withFirstname("Ostap").withLastname("Bender")
                .withPhoto(new File("./src/test/resources/contact.jpg/"))
                .withAddress("221B Baker Street").withHomePhone("789").withMobilePhone("78963145").withWorkPhone("213549873")
                .withEmail("testTest@mail.ru").withEmail2("widh@njv.oo").withEmail3("klnsv@sni.oo").withGroup("Test1"));
    }

    @Test(enabled = true)
    public void testContactModification() {
        Contacts before = app.db().contacts();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstname("Фрай")
                .withLastname("Джей").withAddress("Маркса проспект").withMobilePhone("+78887774433")
                .withEmail("testFR@mail.com").withPhoto(new File("./src/test/resources/contact.jpg/"));
        app.сontact().modify(contact);
        assertThat(app.сontact().count(), equalTo(before.size()));
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
        verifyContactListInUi();
    }
}