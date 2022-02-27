package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Set;

public class ContactDeletionTest extends TestBase {

    @BeforeMethod
    public void ensurePrecondition() {
        app.goTo().homePage();
        app.сontact().createContactIfNotExist(new ContactData().withFirstname("Ostap").withLastname("Bender")
                .withAddress("221B Baker Street").withEmail("testTest@mail.ru").withGroup("Test1"));
    }

    @Test(enabled = true)
    public void testContactDeletion() {
        Set<ContactData> before = app.сontact().all();
        ContactData deletedContact = before.iterator().next();
        app.сontact().delete(deletedContact);
        app.goTo().homePage();
        Set<ContactData> after = app.сontact().all();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(deletedContact);
        Assert.assertEquals(before, after);
    }
}