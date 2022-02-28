package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTest extends TestBase {

    @BeforeMethod
    public void ensurePrecondition() {
        app.goTo().homePage();
        app.сontact().createContactIfNotExist(new ContactData().withFirstname("Ostap").withLastname("Bender")
                .withAddress("221B Baker Street").withEmail("testTest@mail.ru").withGroup("Test1"));
    }

    @Test(enabled = true)
    public void testContactDeletion() {
        Contacts before = app.сontact().all();
        ContactData deletedContact = before.iterator().next();
        app.сontact().delete(deletedContact);
        app.goTo().homePage();
        assertThat(app.сontact().count(), equalTo(before.size() - 1));
        Contacts after = app.сontact().all();
        assertThat(after, equalTo(before.without(deletedContact)));
    }
}