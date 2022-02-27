package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Set;

public class ContactCreationTest extends TestBase {

    @Test(enabled = true)
    public void testContactCreation() throws Exception {
        app.goTo().homePage();
        Set<ContactData> before = app.сontact().all();
        GroupData group = new GroupData().withName("Test2");
        ContactData contact = new ContactData().withFirstname("Ostap").withLastname("Bender").withAddress("221B Baker Street").withMobile("+789456321").withEmail("testTest@mail.ru").withGroup(group.getName());
        app.сontact().createGroupAndContact(group, contact);
        Set<ContactData> after = app.сontact().all();
        Assert.assertEquals(after.size(), before.size() + 1);

        contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt());
        before.add(contact);
        Assert.assertEquals(before, after);
    }
}
