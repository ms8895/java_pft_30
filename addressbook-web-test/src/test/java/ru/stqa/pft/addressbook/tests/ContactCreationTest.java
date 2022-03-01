package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTest extends TestBase {

    @Test(enabled = true)
    public void testContactCreation() throws Exception {
        app.goTo().homePage();
        Contacts before = app.сontact().all();
        GroupData group = new GroupData().withName("Test2");
        ContactData contact = new ContactData().withFirstname("Ostap").withLastname("Bender").withAddress("221B Baker Street").withMobilePhone("+789456321").withEmail("testTest@mail.ru").withGroup(group.getName());
        app.сontact().createGroupAndContact(group, contact);
        assertThat(app.сontact().count(), equalTo(before.size() + 1));
        Contacts after = app.сontact().all();
        assertThat(after, equalTo(before.withAdded(contact
                .withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
    }
}
