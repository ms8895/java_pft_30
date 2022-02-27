package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTest extends TestBase {

    @Test(enabled = true)
    public void testContactCreation() throws Exception {
        app.goTo().homePage();
        List<ContactData> before = app.сontact().list();
        GroupData group = new GroupData().withName("Test2");
        ContactData contact = new ContactData().withFirstname("Ostap").withLastname("Bender").withAddress("221B Baker Street").withMobile("+789456321").withEmail("testTest@mail.ru").withGroup(group.getName());
        app.сontact().createGroupAndContact(group, contact);
        List<ContactData> after = app.сontact().list();
        Assert.assertEquals(after.size(), before.size() + 1);

        before.add(contact);
        Comparator<? super ContactData> byFirstname = (c1, c2) -> CharSequence.compare(c1.getFirstname(), c2.getFirstname());
        before.sort(byFirstname);
        after.sort(byFirstname);
        Assert.assertEquals(before, after);
    }
}
