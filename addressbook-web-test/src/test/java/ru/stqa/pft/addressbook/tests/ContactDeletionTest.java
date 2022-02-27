package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTest extends TestBase {

    @BeforeMethod
    public void ensurePrecondition() {
        app.goTo().homePage();
        app.сontact().createContactIfNotExist(new ContactData("Ostap", "Bender",
                "221B Baker Street", null, "testTest@mail.ru", "Test1"));
    }

    @Test(enabled = true)
    public void testContactDeletion() {
        List<ContactData> before = app.сontact().list();
        int index = before.size() - 1;
        app.сontact().delete(index);
        app.goTo().homePage();
        List<ContactData> after = app.сontact().list();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(index);
        Assert.assertEquals(before, after);

    }
}