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
        app.goTo().gotoHomePage();
        List<ContactData> before = app.getContactHelper().getContactList();
        GroupData group = new GroupData().withName("Test2");
        ContactData contact = new ContactData("Test", "Bender",
                "221B Baker Street", "+789456321", "testTest@mail.ru", group.getName());
        app.group().createGroupIfNotExist(group);
        app.goTo().gotoContactPage();
        app.getContactHelper().createContact(contact);
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() + 1);

        before.add(contact);
        Comparator<? super ContactData> byFirstname = (c1, c2) -> CharSequence.compare(c1.getFirstname(), c2.getFirstname());
        before.sort(byFirstname);
        after.sort(byFirstname);
        Assert.assertEquals(before, after);
    }
}
